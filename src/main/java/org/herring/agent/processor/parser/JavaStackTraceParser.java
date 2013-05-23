package org.herring.agent.processor.parser;


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
        regex = "\\s*at\\s+([\\w\\.$_]+)\\.([\\w$_]+)(\\(.*java)?:(\\d+)\\)(\\n|\\r\\n)";
    }

    @Override
    public void parse(String input) {
        if(regex.length() == 0){
            System.out.println("Regex's length cannot be 0!");
        }

        String trimmed_input = input.trim();
        System.out.println("-------------------------------------");
        System.out.println("Input : "+trimmed_input);
        Pattern headLinePattern = Pattern.compile("([\\w\\.]+)(:.*)?");
        Matcher headLineMatcher = headLinePattern.matcher(trimmed_input);
        if(headLineMatcher.matches())
        {
            System.out.println("===========================");
            System.out.println("Found Pattern!!");
            System.out.println("Founded Line : "+trimmed_input);
            for(int i=0;i<headLineMatcher.groupCount();i++){
                System.out.println("group "+i+" : "+headLineMatcher.group(++i));
            }
            System.out.println("===========================");
        }
        System.out.println("-------------------------------------");
    }

    @Override
    public String getProcessorType() {
        return "Java StackTrace Parser";
    }
}
