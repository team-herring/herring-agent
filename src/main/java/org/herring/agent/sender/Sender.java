package org.herring.agent.sender;

import java.util.List;
import java.util.Map;

/**
 * Sender의 인터페이스
 * User: hyunje
 */
public interface Sender {
    void prepareConnection();

    void sendData(List<Map<String, String>> content);
}
