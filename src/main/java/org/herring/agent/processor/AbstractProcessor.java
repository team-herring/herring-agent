package org.herring.agent.processor;

import org.herring.agent.processor.parser.Parser;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 22.
 * Time: 오전 2:39
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
