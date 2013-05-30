package org.herring.agent.handler;

import org.herring.protocol.NetworkContext;
import org.herring.protocol.handler.MessageHandler;

/**
 * 가장 기본적인 Handler
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 10:39
 */
public final class AgentCruiserConnectionHandler extends MessageHandler {
    @Override
    public void messageArrived(NetworkContext context, Object data) throws Exception {
        System.out.println("Cruiser로부터 메시지가 전송되었습니다.");
    }

    @Override
    public void channelReady(NetworkContext context) throws Exception {
        System.out.println("Cruiser와의 연결이 성공적으로 준비되었습니다.");
    }

    @Override
    public void channelInactive(NetworkContext context) throws Exception {
        System.out.println("Cruiser와의 연결이 끊어졌습니다.");
    }

    @Override
    public void channelClosed(NetworkContext context) throws Exception {
        System.out.println("Cruiser와의 연결이 종료되었습니다.");
    }

    @Override
    public void networkStopped() throws Exception {
        System.out.println("네트워크가 종료되었습니다.");
    }
}
