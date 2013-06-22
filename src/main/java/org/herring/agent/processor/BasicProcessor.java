package org.herring.agent.processor;

import org.herring.agent.HerringAgent;

import java.util.List;
import java.util.Map;

/**
 * 가장 기본적인 형태의 Processor
 * User: hyunje
 */
public class BasicProcessor extends AbstractProcessor {
    @Override
    public void processing(String data) {
        HerringAgent agent = HerringAgent.getInstance();
        List<Map<String,String>> parsedString = this.parser.parse(data);
        System.out.println("Parsed String : "+parsedString);
        agent.notifySender(parsedString);
    }
}
