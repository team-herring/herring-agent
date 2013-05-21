package org.herring.agent.processor.parser;

/**
 * 아무 Parsing도 하지 않고 그대로 읽은 데이터를 전송하는 parser.
 *
 * User: hyunje
 * Date: 13. 5. 21.
 * Time: 오후 5:15
 */
public class NullParser extends AbstractParser {
    @Override
    public void parse(String input) {
    }
}
