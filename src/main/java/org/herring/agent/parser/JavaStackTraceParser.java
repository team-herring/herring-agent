package org.herring.agent.parser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for JavaStackTrace
 * User: hyunje
 * Date: 13. 5. 8.
 * Time: 오후 10:56
 * To change this template use File | Settings | File Templates.
 */
public class JavaStackTraceParser  extends AbstractParser {
    public JavaStackTraceParser(){
//        this.lines = lines;
        this.regex = "\\s*at\\s+([\\w\\.$_]+)\\.([\\w$_]+)(\\(.*java)?:(\\d+)\\)(\\n|\\r\\n)";
    }

    @Override
    public void parse(String input) {
        String trimed_input = input.trim();
        System.out.println("Input : "+trimed_input);
        Pattern headLinePattern = Pattern.compile("([\\w\\.]+)(:.*)?");
        Matcher headLineMatcher = headLinePattern.matcher(trimed_input);
        if(headLineMatcher.matches())
        {
            System.out.println("Found Pattern!!");
            System.out.println(trimed_input);
        }
    }
}
