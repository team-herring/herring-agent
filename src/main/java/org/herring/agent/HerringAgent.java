package org.herring.agent;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.herring.agent.processor.Processor;
import org.herring.agent.watcher.PollingWatcher;
import org.herring.agent.watcher.Watcher;

/**
 * Herring의 Agent 클래스.
 * config.xml에서 설정 값을 가져온다.
 *
 * User: hyunje
 * Date: 13. 5. 20.
 * Time: 오전 10:56
 */
public class HerringAgent {
    XMLConfiguration configuration;
    Watcher watcher;
    Processor processor;

    public HerringAgent() {
        try {
            loadConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void loadConfiguration() throws ConfigurationException {
        configuration = new XMLConfiguration("config.xml");

        String watcherType = configuration.getString("agent.configuration.watcher.type", "polling");
        String watcherTarget = configuration.getString("agent.configuration.watcher.target", "./");
        String watcherDelay = configuration.getString("agent.configuration.watcher.delay", "500");
        setWatcher(watcherType,watcherTarget,watcherDelay);

        String parserType = configuration.getString("agent.configuration.parser.type", "null");

        String targetIP = configuration.getString("agent.configuration.target.ip");
        String targetPort = configuration.getString("agent.configuation.target.port");
    }

    private void setWatcher(String watcherType, String watcherTarget, String watcherDelay) throws NumberFormatException {
        if ("polling".equals(watcherType)) {
            int delay = Integer.parseInt(watcherDelay);
            this.watcher = new PollingWatcher(watcherTarget, delay);
        }else {
            System.out.println("Polling Type Error!");
        }
    }
}
