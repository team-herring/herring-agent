package org.herring.agent.processor.parser;

import jregex.MatchIterator;
import org.herring.agent.processor.Processor;

/**
 * Parser 추상화 객체
 * User: hyunje
 * Date: 13. 5. 8.
 * Time: 오후 10:56
 */
public abstract class AbstractParser implements Processor {
    protected String regex;

    public abstract MatchIterator matchRegex(String input);
    public abstract String getProcessorType();

}
