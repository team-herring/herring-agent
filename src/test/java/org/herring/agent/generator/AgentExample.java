package org.herring.agent.generator;

import org.apache.commons.configuration.ConfigurationException;
import org.herring.agent.HerringAgent;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 16.
 * Time: 오전 11:19
 */
public class AgentExample {
    public static void main(String[] args) throws ConfigurationException {
        HerringAgent agent = HerringAgent.getInstance();
        agent.loadConfiguration();

        agent.attachWatcher();
        agent.attachProcessor();
        agent.attachParser();
        agent.attachSender();
        agent.prepareConnection();

//        agent.connectToCruiser();
        agent.startWatching();
    }
}
