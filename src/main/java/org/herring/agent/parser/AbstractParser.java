package org.herring.agent.parser;

import org.herring.agent.Processor;

/**
 * Parser 추상화 객체
 * User: hyunje
 * Date: 13. 5. 8.
 * Time: 오후 10:56
 */
public abstract class AbstractParser implements Processor {
    protected String regex;
    protected enum COMUMN_NAME{};

    public abstract void parse(String input);
}
