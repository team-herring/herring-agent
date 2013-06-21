package org.herring.agent.watcher.polling;

import org.herring.agent.HerringAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 15.
 * Time: 오후 10:56
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
            agent.parse(addedLine);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
