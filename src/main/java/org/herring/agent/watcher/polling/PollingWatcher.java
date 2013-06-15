package org.herring.agent.watcher.polling;


import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.herring.agent.processor.Processor;
import org.herring.agent.watcher.Watcher;

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
    File targetDirectory = null;
    FileAlterationObserver observer = null;
    FileAlterationMonitor monitor = null;
    PollingEventListener listener = null;

    int delay;

    private static PollingWatcher instance = null;

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

    @Override
    public void setProcessor(Processor processor) {
//        listener.addProcessor(new JavaStackTraceParser());
//        listener.addProcessor(new ApacheWebAccessLogParser());
//        listener.addProcessor(processor);
    }

    public String toString(){
        return  "Type : Polling\n" +
                "Target : "+this.targetDirectory+"\n" +
                "Delay : "+this.delay+"\n";
    }
}
