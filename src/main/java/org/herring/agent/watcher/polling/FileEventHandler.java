package org.herring.agent.watcher.polling;

import java.io.File;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 15.
 * Time: 오후 11:11
 */
public interface FileEventHandler {
    void eventHandle(File catchedFile);
}
