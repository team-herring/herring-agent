package org.herring.agent.sender;

/**
 * Sender의 추상 클래스
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 10:01
 */
public abstract class AbstractSender implements Sender {
    @Override
    public abstract void sendData(String content);
}
