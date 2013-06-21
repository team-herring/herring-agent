package org.herring.agent.processor;

import org.herring.agent.HerringAgent;

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
        String parsedString = this.parser.parse(data);
        agent.notifySender(parsedString);
    }
}
