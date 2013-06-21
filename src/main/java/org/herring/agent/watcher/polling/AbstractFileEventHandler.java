package org.herring.agent.watcher.polling;

import org.herring.agent.util.AgentConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 15.
 * Time: 오후 11:19
 */
public abstract class AbstractFileEventHandler implements FileEventHandler {
    protected AgentConfiguration agentConfiguration;

    public AbstractFileEventHandler() {
        agentConfiguration = AgentConfiguration.getInstance();
    }

    public abstract void eventHandle(File catchedFile);

    protected String ReadContentsFromFile(File file, int position) throws IOException {
        RandomAccessFile addedFile = new RandomAccessFile(file.getAbsolutePath(), "r");
        FileChannel addedFileChannel = addedFile.getChannel();
        ByteBuffer addedFileBuffer = ByteBuffer.allocate(agentConfiguration.BufferSize);
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

    protected int ReadCountFile(File file) throws IOException{
        String countFileName = file.getAbsolutePath()+ "." + agentConfiguration.readCountSuffix;
        File cFile = new File(countFileName);
        if(!cFile.exists()){
            return 0;
        }
        RandomAccessFile countFile = new RandomAccessFile(cFile,"r");
        FileChannel countFileChannel = countFile.getChannel();
        ByteBuffer countFileBuffer = ByteBuffer.allocate(agentConfiguration.BufferSize);
        countFileBuffer.clear();
        countFileChannel.position(0);
        int byteRead = countFileChannel.read(countFileBuffer);
        String countLine = "";
        while (byteRead != -1){
            countFileBuffer.flip();
            while (countFileBuffer.hasRemaining()) {
                char currentChar = (char) countFileBuffer.get();
                countLine += currentChar;
            }
            countFileBuffer.clear();
            byteRead = countFileChannel.read(countFileBuffer);
        }

        countFile.close();
        countFileChannel.close();
        countFileBuffer.clear();
        return Integer.parseInt(countLine);
    }

    protected void CreateCountLineFile(File file, int countLine) throws IOException {
        RandomAccessFile countFile = new RandomAccessFile(file.getAbsolutePath() + "." + agentConfiguration.readCountSuffix, "rw");
        countFile.setLength(0);
        FileChannel countFileChannel = countFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(agentConfiguration.BufferSize);

        System.out.println("New Count : " + countLine);

        buffer.put(String.valueOf(countLine).getBytes());
        buffer.flip();
        while (buffer.hasRemaining())
            countFileChannel.write(buffer);
        countFile.close();
        countFileChannel.close();
        buffer.clear();
    }

}
