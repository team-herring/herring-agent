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
        return !(file.getName().endsWith("." + Constnts.readCountSuffix) ||
                file.getName().startsWith("."));

    }
    public static class Constnts{
        static String readCountSuffix = "count";
        static int BufferSize = 256;
    }
}
