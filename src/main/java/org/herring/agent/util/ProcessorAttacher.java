package org.herring.agent.util;

import org.herring.agent.processor.BasicProcessor;
import org.herring.agent.processor.Processor;

/**
 * Agent에 Processor를 설정해주는 클래스.
 * 설정 값으로부터 Processor를 선택한다.
 * User: hyunje
 */
public class ProcessorAttacher {
    public static Processor attachProcessor(AgentConfiguration configuration) {
        Processor processor = null;
        if("basic".toLowerCase().equals(configuration.processorType))
            processor = new BasicProcessor();
        return processor;
    }
}
