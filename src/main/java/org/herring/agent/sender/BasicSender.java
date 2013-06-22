package org.herring.agent.sender;

import java.util.List;
import java.util.Map;

/**
 * 가장 기본적인 Sender.
 * Processor 에서 Parsing 한 결과를 ClientComponent를 이용해 전송한다.
 * User: hyunje
 */
public final class BasicSender extends AbstractSender {

    @Override
    public void sendData(List<Map<String, String>> content) {
        if(!connectionComponent.isActive()){
            System.out.println("Connection is inactive");
        }
        connectionComponent.getNetworkContext().sendObject(content);
    }
}
