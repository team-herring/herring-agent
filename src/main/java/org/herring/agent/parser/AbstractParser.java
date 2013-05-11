package org.herring.agent.parser;

import org.herring.agent.Processor;

/**
 * Created with IntelliJ IDEA.
 * User: hyunje
 * Date: 13. 5. 8.
 * Time: 오후 10:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractParser implements Processor {
    protected String lines;
    protected String regex;

    public abstract void parse(String input);
}
