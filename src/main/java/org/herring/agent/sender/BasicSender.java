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
    private static BasicSender instance = null;

    private BasicSender() {
    }

    public static BasicSender getInstance() {
        if (instance == null) {
            instance = new BasicSender();
        }
        return instance;
    }

    @Override
    public void setMessageHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }

    @Override
    public void readyForConnection() {
        try {
            XMLConfiguration configuration;
            configuration = new XMLConfiguration("config.xml");
            configuration.load();

            String host = configuration.getString("configuration.sender.target.host");
            int port = configuration.getInt("configuration.sender.target.ip");


            this.clientComponent = new ClientComponent(host, port, codec, messageHandler);
            this.clientComponent.start();
        } catch (Exception e) {
            e.printStackTrace();
            this.clientComponent.stop();
        }
    }

    @Override
    public void setCodec(HerringCodec codec) {
        this.codec = codec;
    }

    @Override
    public void send(String content) {
        if (checkComponentsNull()) {
            System.out.println("Components of Sender must be not null!");
            return;
        }

        //ClientComponent가

    }

    private boolean checkComponentsNull() {
        return !(this.messageHandler == null || this.codec == null);
    }

}
