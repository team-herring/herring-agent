package org.herring.agent.processor;

import org.herring.agent.HerringAgent;

import java.util.List;
import java.util.Map;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 22.
 * Time: 오전 3:21
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
