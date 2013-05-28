package org.herring.agent;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.herring.agent.processor.Processor;
import org.herring.agent.processor.parser.ApacheWebAccessLogParser;
import org.herring.agent.processor.parser.IISLogParser;
import org.herring.agent.processor.parser.JavaStackTraceParser;
import org.herring.agent.processor.parser.NullParser;
import org.herring.agent.util.AgentUtils;
import org.herring.agent.watcher.PollingWatcher;
import org.herring.agent.watcher.Watcher;
import java.util.UUID;

/**
 * Herring의 Agent 클래스.
 * config.xml에서 설정 값을 가져온다.
 *  1. Configuration 읽기
 *  2. 읽은 Configuation 을 이용해 Cruiser 와 통신
 *  3. 통신 성공하면 Agent 실행과 데이터 전송 시작
 *
 * User: hyunje
 * Date: 13. 5. 20.
 * Time: 오전 10:56
 */
public class HerringAgent {
    String agentUUID;
    XMLConfiguration configuration;
    Watcher watcher;
    Processor processor;

    public HerringAgent() {
        try {
            agentUUID = UUID.randomUUID().toString();
            loadConfiguration();




        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        watcher.startWatching();
    }

    private void connectToCruiser(){

    }

    private void loadConfiguration() throws ConfigurationException {
        AgentUtils utils = AgentUtils.getInstance();

        setWatcher(utils.watcherType,utils.watcherTarget,utils.watcherDelay);
        setProcessor(utils.processorType);
    }

    private void setWatcher(String watcherType, String watcherTarget, String watcherDelay) throws NumberFormatException {
        if ("polling".equals(watcherType)) {
            int delay = Integer.parseInt(watcherDelay);
            this.watcher = PollingWatcher.getInstance();
            this.watcher.setConfiguration(watcherTarget,delay);
        }else {
            System.out.println("Polling Type Error!");
        }
    }

    private void setProcessor(String processorType){

        if("IISLogParser".toLowerCase().equals(processorType.toLowerCase())){
            processor = IISLogParser.getInstance();
            this.watcher.setProcessor(processor);
        } else if("ApacheWebAccessLogParser".toLowerCase().equals(processorType.toLowerCase())){
            processor = ApacheWebAccessLogParser.getInstance();
            this.watcher.setProcessor(processor);
        } else if("JavaStackTraceParser".toLowerCase().equals(processorType.toLowerCase())){
            processor = JavaStackTraceParser.getInstance();
            this.watcher.setProcessor(processor);

        } else if("NullParser".toLowerCase().equals(processorType.toLowerCase())){
            processor = NullParser.getInstance();
            this.watcher.setProcessor(processor);
        } else {
            System.out.println("Processor Type Error!");
        }
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("==Watcher==\n").append(watcher.toString());
        builder.append("==Watcher==\n").append(watcher.toString());
        builder.append("Processor Type : ").append(processor.getProcessorType()).append("\n");
        return builder.toString();
    }
}
