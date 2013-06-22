package org.herring.agent.processor;

import org.herring.agent.processor.parser.Parser;

/**
 * Parser Intergace 추상 구현체
 * User: hyunje
 */
public abstract class AbstractProcessor implements Processor{
    protected Parser parser;

    @Override
    public abstract void processing(String data);

    @Override
    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
