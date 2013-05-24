package org.herring.agent.sender.handler;

import org.herring.protocol.NetworkContext;
import org.herring.protocol.handler.MessageHandler;

/**
 * 가장 기본적인 Handler
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 10:39
 */
public final class BasicHandler extends MessageHandler {
    @Override
    public void messageArrived(NetworkContext context, Object data) throws Exception {

    }

    @Override
    public void channelReady(NetworkContext context) throws Exception {
    }

    @Override
    public void channelInactive(NetworkContext context) throws Exception {
    }

    @Override
    public void channelClosed(NetworkContext context) throws Exception {
    }

    @Override
    public void networkStopped() throws Exception {
    }
}
