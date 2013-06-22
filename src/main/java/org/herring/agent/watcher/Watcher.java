package org.herring.agent.watcher;

/**
 * Watcher의 Interface
 * User: hyunje
 */
public interface Watcher {
    void startWatching();

    void setConfiguration(String target, int delay);
}
