package org.herring.agent.util;

import org.herring.agent.watcher.Watcher;
import org.herring.agent.watcher.polling.PollingWatcher;

/**
 * Agent에 알맞는 Watcher를 설정하는 클래스
 * 설정값으로부터 알맞는 Watcher를 선택한다.
 * User: hyunje
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
