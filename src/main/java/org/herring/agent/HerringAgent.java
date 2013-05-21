package org.herring.agent;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.herring.agent.processor.Processor;
import org.herring.agent.watcher.Watcher;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 5. 21.
 * Time: 오전 10:56
 */
public class HerringAgent {
    XMLConfiguration configuration;
    Watcher watcher;
    Processor processor;
    String targetIP;
    String targetPort;

    public HerringAgent() {
        try {
            loadConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void loadConfiguration() throws ConfigurationException {
        configuration = new XMLConfiguration("config.xml");

        String watcherType = configuration.getString("agent.configuration.watcher.type","polling");
        String watcherTarget = configuration.getString("agent.configuration.watcher.target");
        String watcherDelay = configuration.getString("agent.configuration.watcher.delay","500");

        String parserType = configuration.getString("agent.configuration.parser.type","null");

        String targetIp = configuration.getString("agent.configuration.target.ip");
        String targetPort = configuration.getString("agent.configuation.target.port");
    }
}
