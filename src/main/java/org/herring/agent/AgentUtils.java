package org.herring.agent;

import java.io.File;

/**
 * Static Constants for Agent
 * User: hyunje
 * Date: 13. 4. 28.
 * Time: 오후 6:06
 */
public class AgentUtils {

    public static boolean isReadableFile(File file){
        return !(file.getName().endsWith("." + Constants.readCountSuffix) ||
                file.getName().startsWith("."));

    }
    public static class Constants {
        public static String readCountSuffix = "count";
        public static int BufferSize = 256;
    }
}
