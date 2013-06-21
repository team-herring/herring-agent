package org.herring.agent.sender;

import java.util.List;
import java.util.Map;

/**
 * 가장 기본적인 Sender.
 * Watcher 에서 Parsing 한 결과를 ClientComponent를 이용해 전송한다.
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 11:49
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
