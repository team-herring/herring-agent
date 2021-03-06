package org.herring.agent.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;

/**
 * Agent의 설정 파일을 읽고 그 값을 설정하는 클래스
 * User: hyunje
 */
public class AgentConfiguration {


    public String watcherType;
    public String watcherTarget;
    public String watcherDelay;

    public String processorType;

    public String parserType;
    public String rowDelimiter;
    public String columnDelimiter;
    public String dataDelimiter;

    public String readCountSuffix = "count";
    public int BufferSize = 256;

    public String host;
    public String port;

    private AgentConfiguration() {
        try {
            XMLConfiguration configuration = new XMLConfiguration("config.xml");

            watcherType = configuration.getString("configuration.watcher.type", "polling");
            watcherTarget = configuration.getString("configuration.watcher.target", "./");
            watcherDelay = configuration.getString("configuration.watcher.delay", "500");

            processorType = configuration.getString("configuration.processor.tyep","basic");

            parserType = configuration.getString("configuration.parser.type", "nullparser");

            rowDelimiter = configuration.getString("configuration.parser.rowdelimiter");
            columnDelimiter = configuration.getString("configuration.parser.columndelimiter");
            dataDelimiter = configuration.getString("configuration.parser.datadelimiter");

            host = configuration.getString("configuration.sender.target.host");
            port = configuration.getString("configuration.sender.target.port");

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static AgentConfiguration getInstance() {
        return AgentUtilsHolder.INSTANCE;
    }

    public boolean isReadableFile(File file) {
        return !(file.getName().endsWith("." + readCountSuffix) ||
                file.getName().startsWith("."));

    }

    private static final class AgentUtilsHolder {
        private static final AgentConfiguration INSTANCE = new AgentConfiguration();
    }
}
