package org.herring.agent.util;

import org.herring.agent.sender.BasicSender;
import org.herring.agent.sender.Sender;

/**
 * Agent에 Sender를 설정하는 함수.
 * TODO:설정값으로부터 알맞는 Sender를 선택
 * User: hyunje
 */
public class SenderAttacher {
    public static Sender attach(AgentConfiguration configuration){
        Sender sender;
        sender = new BasicSender();
        return sender;
    }
}
