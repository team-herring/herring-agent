package org.herring.agent.sender;

import org.herring.agent.util.AgentConfiguration;
import org.herring.core.cruiser.model.CruiserAgentConnectionCodec;
import org.herring.core.protocol.ClientComponent;
import org.herring.core.protocol.codec.HerringCodec;
import org.herring.core.protocol.handler.AsyncMessageHandler;
import org.herring.core.protocol.handler.MessageHandler;

import java.util.List;
import java.util.Map;

/**
 * Sender의 추상 클래스
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 10:01
 */
public abstract class AbstractSender implements Sender {
    ClientComponent connectionComponent;

    @Override
    public abstract void sendData(List<Map<String,String>> content);

    public void prepareConnection(){
        AgentConfiguration configuration = AgentConfiguration.getInstance();
        HerringCodec codec = new CruiserAgentConnectionCodec();
        MessageHandler messageHandler = new AsyncMessageHandler();
        this.connectionComponent = new ClientComponent(configuration.host,Integer.parseInt(configuration.port),codec,messageHandler);
    }
}
