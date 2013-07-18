package org.herring.agent;

import org.apache.commons.configuration.ConfigurationException;
import org.herring.agent.processor.Processor;
import org.herring.agent.processor.parser.Parser;
import org.herring.agent.sender.Sender;
import org.herring.agent.util.*;
import org.herring.agent.watcher.Watcher;
import org.herring.core.cruiser.model.CruiserAgentConnectionObject;
import org.herring.core.protocol.ClientComponent;
import org.herring.core.protocol.codec.HerringCodec;
import org.herring.core.protocol.codec.SerializableCodec;
import org.herring.core.protocol.handler.MessageHandler;
import org.herring.core.protocol.handler.SyncMessageHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Herring의 Agent 클래스.
 * config.xml에서 설정 값을 가져온다.
 * <p/>
 * Singleton 형태이기 때문에, 여러 target을 watching 하기 위해서는 리팩토링 필요.
 * <p/>
 * User: hyunje
 */
public class HerringAgent {

    private String agentUUID;
    private Watcher watcher;
    private Processor processor;
    private Sender sender;
    private ClientComponent connectionComponent;
    private CruiserAgentConnectionObject connectionObject;
    private boolean isConnected;
    private AgentConfiguration agentConfiguration;

    /**
     * 생성자. 설정 파일 로드
     */
    private HerringAgent() {
        agentUUID = UUID.randomUUID().toString();
        AgentConfiguration agentConfiguration = AgentConfiguration.getInstance();
        connectionObject = new CruiserAgentConnectionObject(agentUUID, true, agentConfiguration.rowDelimiter, agentConfiguration.columnDelimiter, agentConfiguration.dataDelimiter);
    }

    /**
     * 객체 Instance 반환
     *
     * @return HerringAgent Instance 객체
     */
    public static HerringAgent getInstance() {
        return HerringAgentHolder.INSATNCE;
    }

    /**
     * Cruiser로 연결 요청.
     * 동기 연결이므로 Cruiser로부터 응답이 올 때까지 기다린다.
     */
    public void connectToCruiser() {
        try {
            AgentConfiguration agentConfiguration = AgentConfiguration.getInstance();
            MessageHandler handler = new SyncMessageHandler();
            HerringCodec codec = new SerializableCodec();
            CruiserAgentConnectionObject connectionObject = new CruiserAgentConnectionObject(this.agentUUID, true, agentConfiguration.rowDelimiter, agentConfiguration.columnDelimiter, agentConfiguration.dataDelimiter);
            connectionComponent = new ClientComponent(agentConfiguration.host, Integer.parseInt(agentConfiguration.port), codec, handler);
            connectionComponent.start();

            connectionComponent.getNetworkContext().sendObject(connectionObject);
            connectionComponent.getNetworkContext().waitUntil("received");

            isConnected = (Boolean) connectionComponent.getNetworkContext().getMessageFromQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Directory Polling 시작
     */
    public void startWatching() {
        watcher.startWatching();
    }

    /**
     * Configuration
     *
     * @throws ConfigurationException
     */
    public void loadConfiguration() throws ConfigurationException {
        this.agentConfiguration = AgentConfiguration.getInstance();
    }


    //-------------------------------------------------------
    //----------------Watcher 와 관련된 Methods----------------

    /**
     * 설정 파일로부터 Watcher를 설정
     */
    public void attachWatcher() {
        try {
            this.watcher = WatcherAttatcher.attach(this.agentConfiguration);
        } catch (NumberFormatException e) {
            System.out.println("Casting Error");
            e.printStackTrace();
        }

    }

    /**
     * 사용자의 직접 지정을 통한 Watcher 설정
     *
     * @param watcher 설정파일이 아닌 직접 설정한 Watcher
     */
    public void attachWatcher(Watcher watcher) {
        this.watcher = watcher;
    }

    /**
     * Watcher의 수행 결과 -> ex)파일의 변경된 부분을 Processor 에 전달
     *
     * @param contents Watcher의 수행 결과
     */
    public void notifyProcessor(String contents) {
        this.processor.processing(contents);
    }
    //-------------------------------------------------------
    //-------------------------------------------------------


    //---------------------------------------------------------
    //----------------Processor 와 관련된 Methods----------------

    /**
     * 설정 파일로부터 Processor를 설정
     */
    public void attachProcessor() {
        this.processor = ProcessorAttacher.attachProcessor(this.agentConfiguration);
    }

    /**
     * 사용자 직접 지정을 통한 Processor 설정
     *
     * @param processor 사용자가 직접 지정한 Processor
     */
    public void attachProcessor(Processor processor) {
        this.processor = processor;
    }

    /**
     * 설정파일에 정의된 Parser 설정
     */
    public void attachParser() {
        if(processor == null){
            System.out.println("Processor is Null!");
            return;
        }
        this.processor.setParser(ParserAttacher.attachParser(this.agentConfiguration));
    }

    /**
     * 사용자가 정의한 Parser 설정
     *
     * @param parser 사용자가 정의한 Parser
     */
    public void attachParser(Parser parser) {
        this.processor.setParser(parser);
    }

    /**
     * Processor가 수행한 결과 -> ex)파싱된 결과를 Sender로 전송
     *
     * @param contents Processor가 수행한 결과
     */
    public void notifySender(List<Map<String,String>> contents) {
//        System.out.println("Agent's Data : "+contents);
        this.sender.sendData(contents);
    }
    //---------------------------------------------------------
    //---------------------------------------------------------


    //--------------------------------------------------------
    //-----------------Sender 와 관련된 Methods-----------------
    public void prepareConnection(){
        this.sender.prepareConnection();
    }

    public void attachSender(){
        this.sender = SenderAttacher.attach(agentConfiguration);
    }


    //---------------------------------------------------------
    //---------------------------------------------------------


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("==Watcher==\n").append(watcher.toString());
//        builder.append("==Watcher==\n").append(watcher.toString());
//        builder.append("Processor Type : ").append(processor.getProcessorType()).append("\n");
        return builder.toString();
    }

    public CruiserAgentConnectionObject getConnectionObject() {
        return connectionObject;
    }

    public boolean isConnected() {
        return isConnected;
    }

    private static final class HerringAgentHolder {
        private static final HerringAgent INSATNCE = new HerringAgent();
    }
}
