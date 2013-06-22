package org.herring.agent.watcher.polling;


import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.herring.agent.watcher.Watcher;

import java.io.File;


/**
 * 설정된 경로를 Watching할 Watcher 구현체
 * 현재 버전에서는 경로는 폴더여야만 한다.
 * <p/>
 * User: hyunje
 */
public class PollingWatcher implements Watcher {
    private static PollingWatcher instance = null;
    File targetDirectory = null;
    FileAlterationObserver observer = null;
    FileAlterationMonitor monitor = null;
    PollingEventListener listener = null;
    int delay;

    public static PollingWatcher getInstance() {
        if (instance == null)
            instance = new PollingWatcher();

        return instance;
    }

    public void setConfiguration(String target, int delay) {
        targetDirectory = new File(target);

        listener = new PollingEventListener();

        observer = new FileAlterationObserver(targetDirectory);
        observer.addListener(listener);


        this.delay = delay;
        monitor = new FileAlterationMonitor(this.delay);
//        monitor = new FileAlterationMonitor(500);
        monitor.addObserver(observer);
    }

    public void startWatching() {
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Type : Polling\n" +
                "Target : " + this.targetDirectory + "\n" +
                "Delay : " + this.delay + "\n";
    }
}
