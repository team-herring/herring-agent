package org.herring.agent.util;

import org.herring.agent.processor.BasicProcessor;
import org.herring.agent.processor.Processor;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 21.
 * Time: 오후 11:47
 */
public class ProcessorAttacher {
    public static Processor attachProcessor(AgentConfiguration configuration) {
        Processor processor = null;
        if("basic".toLowerCase().equals(configuration.processorType))
            processor = new BasicProcessor();
        return processor;
    }
}
