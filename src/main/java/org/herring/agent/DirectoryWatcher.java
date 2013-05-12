package org.herring.agent;


import org.herring.agent.parser.JavaStackTraceParser;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;


/**
 * Class for polling a directory.
 * The directory must be last directory in current version.
 * <p/>
 * User: hyunje
 * Date: 13. 4. 23.
 * Time: 오후 9:39
 */
public class DirectoryWatcher  implements Watcher  {
    File targetDirectory;
    FileAlterationObserver observer;
    FileAlterationMonitor monitor;
    DirectoryWatchingEventListener listener;

    public DirectoryWatcher(String directory) {
        targetDirectory = new File(directory);

        listener = new DirectoryWatchingEventListener();
        listener.addParser(new JavaStackTraceParser());

        observer = new FileAlterationObserver(targetDirectory);
        observer.addListener(listener);


        monitor = new FileAlterationMonitor(500);
        monitor.addObserver(observer);
    }

    public void startWatching(){
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
