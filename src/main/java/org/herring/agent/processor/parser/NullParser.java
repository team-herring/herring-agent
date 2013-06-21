package org.herring.agent.processor.parser;

import jregex.Matcher;

/**
 * 아무 Parsing도 하지 않고 그대로 읽은 데이터를 전송하는 parser.
 * <p/>
 * User: hyunje
 * Date: 13. 5. 21.
 * Time: 오후 5:15
 */
public class NullParser extends AbstractParser {
    private static NullParser instance = null;

    private NullParser() {
    }

    public static NullParser getInstance() {
        if (instance == null) {
            instance = new NullParser();
        }
        return instance;
    }

    @Override
    public Matcher matchRegex(String input) {
        return null;
    }

    @Override
    public String packageMatchingResult(Matcher matcher) {
        return "";
    }

}
