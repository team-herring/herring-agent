package org.herring.agent.sender;

import java.util.List;
import java.util.Map;

/**
 * Sender의 인터페이스
 * User: hyunje
 * Date: 13. 5. 24.
 * Time: 오전 9:14
 */
public interface Sender {
    void prepareConnection();

    void sendData(List<Map<String, String>> content);
}
