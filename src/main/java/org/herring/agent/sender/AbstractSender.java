package org.herring.agent.sender;

import org.herring.protocol.ClientComponent;
import org.herring.protocol.codec.HerringCodec;
import org.herring.protocol.handler.MessageHandler;

/**
 * Sender의 추상 클래스
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 10:01
 */
public abstract class AbstractSender implements Sender{
    String uuid;
    ClientComponent clientComponent;
    MessageHandler messageHandler;
    HerringCodec codec;

    public abstract void setMessageHandler(MessageHandler handler);
    public abstract void readyForConnection();
    public abstract void setCodec(HerringCodec codec);
    @Override
    public abstract void send(String content);
}
