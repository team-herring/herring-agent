package org.herring.agent.processor;

/**
 * Interface for Processor.
 * Processor parses some lines using specific rules.
 *
 * User: hyunje
 * Date: 13. 5. 7.
 * Time: 오후 3:28
 */
public interface Processor {
    void parse(String input);
    String getProcessorType();
}
