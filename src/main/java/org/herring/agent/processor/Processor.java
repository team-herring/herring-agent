package org.herring.agent.processor;

import org.herring.agent.processor.parser.Parser;

/**
 * 정규식 Parsing을 위한 Processor.
 * Processor에 parser를 설정해서 동작시키는 형식
 * <p/>
 * User: hyunje
 */
public interface Processor {
    void setParser(Parser parser);

    void processing(String data);
}
