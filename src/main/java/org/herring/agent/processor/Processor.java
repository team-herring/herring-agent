package org.herring.agent.processor;

import org.herring.agent.processor.parser.Parser;

/**
 * 정규식 Parsing을 위한 Processor.
 * Log 의 형태에 따라 Parser가 존재하며, Parsing의 결과를 MatchIterator로 반환한다.
 * Sender에서는 이 MatchIterator를 받아서 Host로 전송 할 것.
 * <p/>
 * User: hyunje
 * Date: 13. 5. 7.
 * Time: 오후 3:28
 */
public interface Processor {
    void setParser(Parser parser);

    void processing(String data);
}
