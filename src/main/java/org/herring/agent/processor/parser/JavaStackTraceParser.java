package org.herring.agent.processor.parser;


import jregex.MatchIterator;
import jregex.MatchResult;
import org.herring.agent.util.AgentUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java Stack Trace 를 위한 Parser.
 * 하지만 아직 미완. 수정되어야 한다.
 * User: hyunje
 * Date: 13. 5. 8.
 * Time: 오후 10:56
 */
public class JavaStackTraceParser extends AbstractParser {
    private static JavaStackTraceParser instance = null;

    private JavaStackTraceParser() {
        regex = "\\s*at\\s+([\\w\\.$_]+)\\.([\\w$_]+)(\\(.*java)?:(\\d+)\\)(\\n|\\r\\n)";
    }

    public static JavaStackTraceParser getInstance() {
        if (instance == null) {
            instance = new JavaStackTraceParser();
        }
        return instance;
    }

    /**
     * 수정되어야 한다.
     * @param input
     * @return
     */
    @Override
    public MatchIterator matchRegex(String input) {
        /*
        수정되어야 하는 함수.
         */
        if (regex.length() == 0) {
            System.out.println("Regex's length cannot be 0!");
        }

        String trimmed_input = input.trim();
        System.out.println("-------------------------------------");
        System.out.println("Input : " + trimmed_input);
        Pattern headLinePattern = Pattern.compile("([\\w\\.]+)(:.*)?");
        Matcher headLineMatcher = headLinePattern.matcher(trimmed_input);
        if (headLineMatcher.matches()) {
            System.out.println("===========================");
            System.out.println("Found Pattern!!");
            System.out.println("Founded Line : " + trimmed_input);
            for (int i = 0; i < headLineMatcher.groupCount(); i++) {
                System.out.println("group " + i + " : " + headLineMatcher.group(++i));
            }
            System.out.println("===========================");
        }
        System.out.println("-------------------------------------");


        return null;
    }

    @Override
    public String getProcessorType() {
        return "Java StackTrace Parser";
    }

    @Override
    public String matchIteratorToString(MatchIterator matchIterator) {
        AgentUtils utils = AgentUtils.getInstance();
        String rowDelim = utils.rowDelimiter;
        String columnDelim = utils.columnDelimiter;
        String dataDelim = utils.dataDelimiter;

        StringBuilder builder = new StringBuilder();
        while (matchIterator.hasMore()){
            MatchResult matchResult = matchIterator.nextMatch();
            for(COLUMN_NAME column_name : COLUMN_NAME.values()){
                builder.append(column_name).append(dataDelim).append(matchResult.group(column_name.toString())).append(columnDelim);
            }
            builder.substring(0,builder.toString().length()-columnDelim.length());
            builder.append(rowDelim);
        }
        return builder.toString();
    }

    enum COLUMN_NAME{}
}
