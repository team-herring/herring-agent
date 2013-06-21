package org.herring.agent.util;

import org.herring.agent.sender.BasicSender;
import org.herring.agent.sender.Sender;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 22.
 * Time: 오전 7:49
 */
public class SenderAttacher {
    public static Sender attach(AgentConfiguration configuration){
        Sender sender;
        sender = new BasicSender();
        return sender;
    }
}
