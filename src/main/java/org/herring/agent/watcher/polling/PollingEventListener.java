package org.herring.agent.watcher.polling;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.herring.agent.util.AgentConfiguration;

import java.io.File;

/**
 * Event Listener for PollingWatcher
 * User: hyunje
 * Date: 13. 5. 5.
 * Time: 오후 10:08
 */
public class PollingEventListener implements FileAlterationListener {
    AgentConfiguration utils;

    public PollingEventListener(){
        utils = AgentConfiguration.getInstance();
    }


    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {
//            System.out.println("Start");
    }

    @Override
    public void onDirectoryCreate(File file) {
        System.out.println("Created Directory : " + file.getName());
    }

    @Override
    public void onDirectoryChange(File file) {
        System.out.println("Changed Directory : " + file.getName());
    }

    @Override
    public void onDirectoryDelete(File file) {
        System.out.println("Deleted Directory : " + file.getName());
    }

    @Override
    public void onFileCreate(File file) {
        FileEventHandler handler = new FileCreateEventHandler();
        handler.eventHandle(file);
    }

    @Override
    public void onFileChange(File file) {
        FileEventHandler handler = new FileChangeEventHandler();
        handler.eventHandle(file);
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("File was deleted : " + file.getName());
    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
    }
}