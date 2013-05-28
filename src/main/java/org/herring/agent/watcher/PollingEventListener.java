package org.herring.agent.watcher;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.herring.agent.HerringAgent;
import org.herring.agent.util.AgentUtils;
import org.herring.agent.processor.Processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Event Listener for PollingWatcher
 * User: hyunje
 * Date: 13. 5. 5.
 * Time: 오후 10:08
 */
public class PollingEventListener implements FileAlterationListener {
//    Processor parser;
    AgentUtils utils;

    public PollingEventListener(){
        utils = AgentUtils.getInstance();
    }

//    public void addProcessor(Processor processor){
//        this.parser = processor;
//    }

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
        if (!utils.isReadableFile(file)) return;

        System.out.println("File was created : " + file.getName());
        System.out.println("Read file : ");
        try {


            //Read from added File
            String readLine = ReadContentsFromFile(file, 0);

//            Parse read line
//            parser.matchRegex(readLine);

            //Pass read lines to Processor
            HerringAgent agent = HerringAgent.getInstance();
            agent.parse(readLine);


            //Create a counted line number file
            CreateCountLineFile(file, readLine.length());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFileChange(File file) {
        if (!utils.isReadableFile(file)) return;
        System.out.println("File was changed : " + file.getName());
        try {
            File countFile = new File(file.getAbsolutePath() + "." + utils.readCountSuffix);
            if (!countFile.exists()) {
                System.out.println("Count file does not exist.");
                return;
            }

            // Read previous counted line
            String countLineNum = ReadContentsFromFile(countFile, 0);
            int preReadLine = Integer.parseInt(countLineNum);

            // Read from counted char
            String addedLine = ReadContentsFromFile(file, preReadLine);
//            System.out.print("Added Line : ");System.out.println(addedLine);

            //update counted char
            int postReadLine = preReadLine + addedLine.length();
            CreateCountLineFile(file,postReadLine);

            //Regular Expression Match
//            parser.parse(addedLine);

            //Pass read lines to Processor
            HerringAgent agent = HerringAgent.getInstance();
            agent.parse(addedLine);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String ReadContentsFromFile(File file, int position) throws IOException {
        RandomAccessFile addedFile = new RandomAccessFile(file.getAbsolutePath(), "r");
        FileChannel addedFileChannel = addedFile.getChannel();
        ByteBuffer addedFileBuffer = ByteBuffer.allocate(utils.BufferSize);
        addedFileBuffer.clear();
        addedFileChannel.position(position);
        int byteRead = addedFileChannel.read(addedFileBuffer);
        String addedLine = "";
        while (byteRead != -1) {
            addedFileBuffer.flip();
//                System.out.print("Read Line : ");
            while (addedFileBuffer.hasRemaining()) {
                char currentChar = (char) addedFileBuffer.get();
                addedLine += currentChar;
//                    System.out.print(currentChar);
            }
            addedFileBuffer.clear();
            byteRead = addedFileChannel.read(addedFileBuffer);
//                System.out.println();
        }
//            System.out.print("Whole Read Line : ");
//            System.out.println(addedLine);
        addedFile.close();
        addedFileChannel.close();
        addedFileBuffer.clear();

        return addedLine;
    }

    private void CreateCountLineFile(File file, int countLine) throws IOException{
        RandomAccessFile countFile = new RandomAccessFile(file.getAbsolutePath()+"."+ utils.readCountSuffix,"rw");
        countFile.setLength(0);
        FileChannel countFileChannel = countFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(utils.BufferSize);

        System.out.println("New Count : " + countLine);

        buffer.put(String.valueOf(countLine).getBytes());
        buffer.flip();
        while (buffer.hasRemaining())
            countFileChannel.write(buffer);
        countFile.close();
        countFileChannel.close();
        buffer.clear();
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("File was deleted : " + file.getName());
    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
    }
}