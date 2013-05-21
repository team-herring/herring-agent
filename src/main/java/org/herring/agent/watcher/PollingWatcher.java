package org.herring.agent.watcher;


import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.herring.agent.processor.parser.IISLogParser;

import java.io.File;


/**
 * Class for polling a directory.
 * The directory must be last directory in current version.
 * <p/>
 * User: hyunje
 * Date: 13. 4. 23.
 * Time: 오후 9:39
 */
public class PollingWatcher implements Watcher {
    File targetDirectory;
    FileAlterationObserver observer;
    FileAlterationMonitor monitor;
    DirectoryWatchingEventListener listener;

    public PollingWatcher(String directory) {
        targetDirectory = new File(directory);

        listener = new DirectoryWatchingEventListener();
//        listener.addParser(new JavaStackTraceParser());
//        listener.addParser(new ApacheWebAccessLogParser());
        listener.addParser(new IISLogParser());
        observer = new FileAlterationObserver(targetDirectory);
        observer.addListener(listener);


        monitor = new FileAlterationMonitor();
        monitor.addObserver(observer);
    }

    public void startWatching() {
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
