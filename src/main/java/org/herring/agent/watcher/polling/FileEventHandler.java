package org.herring.agent.watcher.polling;

import java.io.File;

/**
 * 파일이 변경되거나, 추가되었을 때의 이벤트를 처리할 Handler
 * EventHandler 에서 Agent의 Instance를 통해 Processor로 데이터를 전송한다
 * User: hyunje
 */
public interface FileEventHandler {
    void eventHandle(File catchedFile);
}
