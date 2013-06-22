package org.herring.agent.watcher.polling;

import org.herring.agent.HerringAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 파일이 새로 생성 된 이벤트를 캐치할 핸들러
 * User: hyunje
 */
public class FileCreateEventHandler extends AbstractFileEventHandler {
    @Override
    public void eventHandle(File catchedFile) {
        if (!agentConfiguration.isReadableFile(catchedFile)) return;

        System.out.println("File was created : " + catchedFile.getName());
        System.out.println("Read file : ");
        try {
            //Read from added File
            String readLine = ReadContentsFromFile(catchedFile, 0);

//            Parse read line
//            parser.matchRegex(readLine);

            //Create a counted line number file
            CreateCountLineFile(catchedFile, readLine.length());

            //Pass read lines to Processor
            HerringAgent agent = HerringAgent.getInstance();
            agent.notifyProcessor(readLine);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
