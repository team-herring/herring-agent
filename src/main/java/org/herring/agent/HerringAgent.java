package org.herring.agent;

import jregex.MatchIterator;
import jregex.Matcher;
import org.apache.commons.configuration.ConfigurationException;
import org.herring.agent.processor.Processor;
import org.herring.agent.processor.parser.ApacheWebAccessLogParser;
import org.herring.agent.processor.parser.IISLogParser;
import org.herring.agent.processor.parser.NullParser;
import org.herring.agent.sender.BasicSender;
import org.herring.agent.sender.Sender;
import org.herring.agent.util.AgentUtils;
import org.herring.agent.watcher.Watcher;
import org.herring.agent.watcher.polling.PollingWatcher;
import org.herring.core.cruiser.model.CruiserAgentConnectionCodec;
import org.herring.core.cruiser.model.CruiserAgentConnectionObject;
import org.herring.core.protocol.ClientComponent;
import org.herring.core.protocol.codec.HerringCodec;
import org.herring.core.protocol.codec.SerializableCodec;
import org.herring.core.protocol.handler.MessageHandler;
import org.herring.core.protocol.handler.SyncMessageHandler;

import java.util.UUID;

/**
 * Herring의 Agent 클래스.
 * config.xml에서 설정 값을 가져온다.
 * 1. Configuration 읽기
 * 2. 읽은 Configuation 을 이용해 Cruiser 와 통신
 * 3. 통신 성공하면 Agent 실행과 데이터 전송 시작
 * <p/>
 * Singleton 형태이기 때문에, 여러 target을 watching 하기 위해서는 리팩토링 필요.
 * <p/>
 * User: hyunje
 * Date: 13. 5. 20.
 * Time: 오전 10:56
 */
public class HerringAgent {

    private static HerringAgent instance = null;
    private boolean isConnected = false;
    String agentUUID;
    Watcher watcher;
    Processor processor;
    Sender sender;
    ClientComponent connectionComponent;
    CruiserAgentConnectionObject connectionObject;

    /**
     * 생성자. 설정 파일 로드
     */
    private HerringAgent() {
        try {
            agentUUID = UUID.randomUUID().toString();
            AgentUtils utils = AgentUtils.getInstance();
            connectionObject = new CruiserAgentConnectionObject(agentUUID, true, utils.rowDelimiter, utils.columnDelimiter, utils.dataDelimiter);
            loadConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 객체 Instance 반환
     * @return HerringAgent Instance 객체
     */
    public static HerringAgent getInstance() {
        if (instance == null) {
            instance = new HerringAgent();
        }
        return instance;
    }

    /**
     * Cruiser로 연결 요청.
     * 동기 연결이므로 Cruiser로부터 응답이 올 때까지 기다린다.
     */
    public void connectToCruiser() {
        try {
            AgentUtils utils = AgentUtils.getInstance();
            MessageHandler handler = new SyncMessageHandler();
            HerringCodec codec = new SerializableCodec();
            CruiserAgentConnectionObject connectionObject = new CruiserAgentConnectionObject(this.agentUUID, true, utils.rowDelimiter, utils.columnDelimiter, utils.dataDelimiter);
            connectionComponent = new ClientComponent(utils.host, Integer.parseInt(utils.port), codec, handler);
            connectionComponent.start();

            connectionComponent.getNetworkContext().sendObject(connectionObject);
            connectionComponent.getNetworkContext().waitUntil("received");

            isConnected = (Boolean)connectionComponent.getNetworkContext().getMessageFromQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Directory Polling 시작
     */
    public void start() {
        watcher.startWatching();
    }


    /**
     * WatchingEventListener 에서 감지된 String을 Agent의 Processor로 전달하여 Regular Expression Mathcing 수행
     * Parsing 된 결과를 Sender를 통해 전송한다.
     * <p/>
     * TODO: Extract from Agent
     *
     * @param data EventListener에서 감지된 String
     */
    public void parse(String data) {
        Matcher matcher = processor.matchRegex(data);
        MatchIterator matchIterator = matcher.findAll();
//        int rowCount = matchIterator.count();
        //Match Result 를 통해서 Sender에 Matching 된 결과 전송.
        //Sender에서는 Match Result를 이용해 Host에 전송.
        String parsedString = processor.packageMatchingResult(matcher);

        //rowCount와 parsedString을 package 해서 Cruiser로 전송
        connectionComponent.getNetworkContext().sendObject(parsedString);
    }

    private void loadConfiguration() throws ConfigurationException {
        AgentUtils utils = AgentUtils.getInstance();

        setWatcher(utils.watcherType, utils.watcherTarget, utils.watcherDelay);
        setProcessor(utils.processorType);
        setSender();
    }

    private void setSender() {
        this.sender = BasicSender.getInstance();

    }

    /**
     * 설정 파일을 읽을 때, Watcher를 설정하는 함수
     * @param watcherType watcher의 종류
     * @param watcherTarget polling할 target
     * @param watcherDelay polling delay
     * @throws NumberFormatException
     */
    private void setWatcher(String watcherType, String watcherTarget, String watcherDelay) throws NumberFormatException {
        if ("polling".equals(watcherType)) {
            int delay = Integer.parseInt(watcherDelay);
            this.watcher = PollingWatcher.getInstance();
            this.watcher.setConfiguration(watcherTarget, delay);
        } else {
            System.out.println("Polling Type Error!");
        }
    }

    /**
     * 설정 파일을 읽을 때, Processor를 설정하는 함수
     * @param processorType processor의 종
     */
    private void setProcessor(String processorType) {

        if ("IISLogParser".toLowerCase().equals(processorType.toLowerCase())) {
            processor = IISLogParser.getInstance();
            this.watcher.setProcessor(processor);
        } else if ("ApacheWebAccessLogParser".toLowerCase().equals(processorType.toLowerCase())) {
            processor = ApacheWebAccessLogParser.getInstance();
            this.watcher.setProcessor(processor);
        } else if ("NullParser".toLowerCase().equals(processorType.toLowerCase())) {
            processor = NullParser.getInstance();
            this.watcher.setProcessor(processor);
        } else {
            System.out.println("Processor Type Error!");
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("==Watcher==\n").append(watcher.toString());
//        builder.append("==Watcher==\n").append(watcher.toString());
        builder.append("Processor Type : ").append(processor.getProcessorType()).append("\n");
        return builder.toString();
    }
}
