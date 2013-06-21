package org.herring.agent.sender;

import org.apache.commons.configuration.XMLConfiguration;
import org.herring.core.protocol.ClientComponent;
import org.herring.core.protocol.codec.HerringCodec;
import org.herring.core.protocol.handler.MessageHandler;

/**
 * 가장 기본적인 Sender.
 * Watcher 에서 Parsing 한 결과를 ClientComponent를 이용해 전송한다.
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 11:49
 */
public final class BasicSender extends AbstractSender {

    @Override
    public void sendData(String content) {

    }
}
