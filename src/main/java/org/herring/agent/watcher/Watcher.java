package org.herring.agent.watcher;

import org.herring.agent.processor.Processor;

/**
 * Interface for watching something.
 * User: hyunje
 * Date: 13. 5. 7.
 * Time: 오후 12:31
 */
public interface Watcher {
    void startWatching();
    void setProcessor(Processor processor);
}
