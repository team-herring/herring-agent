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
public class FileCreateEventHandler extends AbstractFileEventHandler{
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

            //Pass read lines to Processor
            HerringAgent agent = HerringAgent.getInstance();
            agent.notifyProcessor(readLine);


            //Create a counted line number file
            CreateCountLineFile(catchedFile, readLine.length());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
