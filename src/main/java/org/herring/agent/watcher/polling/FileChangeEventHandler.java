package org.herring.agent.watcher.polling;

import org.herring.agent.HerringAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 파일이 변경된 이벤트를 캐치할 핸들러
 * User: hyunje
 */
public class FileChangeEventHandler extends AbstractFileEventHandler {
    @Override
    public void eventHandle(File catchedFile) {
        if (!agentConfiguration.isReadableFile(catchedFile)) return;
        System.out.println("File was changed : " + catchedFile.getName());
        try {
            File countFile = new File(catchedFile.getAbsolutePath() + "." + agentConfiguration.readCountSuffix);
            if (!countFile.exists()) {
                System.out.println("Count file does not exist.");
                return;
            }

            // Read previous counted line
            String countLineNum = ReadContentsFromFile(countFile, 0);
            int preReadLine = Integer.parseInt(countLineNum);

            // Read from counted char
            String addedLine = ReadContentsFromFile(catchedFile, preReadLine);
//            System.out.print("Added Line : ");System.out.println(addedLine);

            //update counted char
            int postReadLine = preReadLine + addedLine.length();
            CreateCountLineFile(catchedFile,postReadLine);

            //Pass read lines to Processor
            HerringAgent agent = HerringAgent.getInstance();
            agent.notifyProcessor(addedLine);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
