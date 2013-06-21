package org.herring.agent.util;

import org.herring.agent.processor.parser.ApacheWebAccessLogParser;
import org.herring.agent.processor.parser.IISLogParser;
import org.herring.agent.processor.parser.NullParser;
import org.herring.agent.processor.parser.Parser;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 22.
 * Time: 오전 3:45
 */
public class ParserAttacher {
    public static Parser attachParser(AgentConfiguration configuration){
        Parser parser = null;
        if ("IISLogParser".toLowerCase().equals(configuration.parserType.toLowerCase())) {
            parser = IISLogParser.getInstance();
        } else if ("ApacheWebAccessLogParser".toLowerCase().equals(configuration.parserType.toLowerCase())) {
            parser = ApacheWebAccessLogParser.getInstance();
        } else if ("NullParser".toLowerCase().equals(configuration.parserType.toLowerCase())) {
            parser = NullParser.getInstance();
        } else {
            System.out.println("Processor Type Error!");
        }
        return parser;
    }
}
