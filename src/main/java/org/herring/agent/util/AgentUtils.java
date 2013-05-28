package org.herring.agent.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;

/**
 * Static Constants for Agent
 * User: hyunje
 * Date: 13. 4. 28.
 * Time: 오후 6:06
 */
public class AgentUtils {
    private XMLConfiguration configuration;

    public String watcherType;
    public String watcherTarget;
    public String watcherDelay;
    public String processorType;
    public String rowDelimiter;
    public String columnDelimiter;
    public String dataDelimiter;

    public String readCountSuffix = "count";
    public int BufferSize = 256;

    private static AgentUtils instance = null;

    public static AgentUtils getInstance(){
        if(instance == null){
            instance = new AgentUtils();
        }
        return instance;
    }


    private AgentUtils(){
        try {
            configuration = new XMLConfiguration("config.xml");
            configuration.load();

            watcherType = configuration.getString("configuration.watcher.type", "polling");
            watcherTarget = configuration.getString("configuration.watcher.target", "./");
            watcherDelay = configuration.getString("configuration.watcher.delay", "500");

            processorType = configuration.getString("configuration.processor.type", "nullparser");

            rowDelimiter = configuration.getString("configuration.sender.processor.rowdelimiter");
            columnDelimiter = configuration.getString("configuration.sender.processor.columndelimiter");
            dataDelimiter = configuration.getString("configuration.sender.processor.datadelimiter");


        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }








    public boolean isReadableFile(File file){
        return !(file.getName().endsWith("." + readCountSuffix) ||
                file.getName().startsWith("."));

    }
    public static class Constants {

    }
}
