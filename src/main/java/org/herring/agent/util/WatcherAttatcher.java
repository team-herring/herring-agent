package org.herring.agent.util;

import org.herring.agent.watcher.Watcher;
import org.herring.agent.watcher.polling.PollingWatcher;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 21.
 * Time: 오후 11:21
 */
public class WatcherAttatcher {
    public static Watcher attach(AgentConfiguration configuration) throws NumberFormatException{
        Watcher watcher = null;
        if ("polling".equals(configuration.watcherType)) {
            int delay = Integer.parseInt(configuration.watcherDelay);
            watcher = PollingWatcher.getInstance();
            watcher.setConfiguration(configuration.watcherTarget, delay);
        } else {
            System.out.println("Polling Type Error!");
        }
        return watcher;
    }
}
