package org.herring.agent.generator;

import org.herring.agent.HerringAgent;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 16.
 * Time: 오전 11:19
 */
public class AgentExample {
    public static void main(String[] args){
        HerringAgent agent = HerringAgent.getInstance();
        agent.connectToCruiser();
        agent.start();
    }
}
