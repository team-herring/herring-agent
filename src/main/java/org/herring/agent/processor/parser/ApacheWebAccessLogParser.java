package org.herring.agent.processor.parser;

import jregex.MatchIterator;
import jregex.MatchResult;
import jregex.Matcher;
import jregex.Pattern;
import org.herring.agent.util.AgentUtils;

/**
 * Apache Web Access Log 파서
 * User: hyunje
 * Date: 13. 5. 14.
 * Time: 오전 10:43
 * To change this template use File | Settings | File Templates.
 */
public class ApacheWebAccessLogParser extends AbstractParser {
    private static ApacheWebAccessLogParser instance = null;

    private ApacheWebAccessLogParser() {
        regex = "({" + COLUMN_NAME.ip_address + "}\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s" +
                "({" + COLUMN_NAME.username + "}.+?)\\s" +
                "({" + COLUMN_NAME.user_id + "}\\d+?|\\-)\\s\\[" +
                "({" + COLUMN_NAME.day + "}\\d\\d)/" +
                "({" + COLUMN_NAME.month + "}\\w\\w\\w)/" +
                "({" + COLUMN_NAME.year + "}\\d\\d\\d\\d):" +
                "({" + COLUMN_NAME.hour + "}\\d\\d):" +
                "({" + COLUMN_NAME.minute + "}\\d\\d):" +
                "({" + COLUMN_NAME.second + "}\\d\\d)\\s" +
                "({" + COLUMN_NAME.time_zone + "}[-\\+]\\d\\d\\d\\d)\\]\\s\"" +
                "({" + COLUMN_NAME.method + "}GET|POST|PUT|DELETE|HEAD|TRACE|OPTION|CONNECT)\\s" +
                "({" + COLUMN_NAME.url + "}.+?)\\s" +
                "({" + COLUMN_NAME.protocol + "}.+?)\"\\s" +
                "({" + COLUMN_NAME.status_code + "}\\d\\d\\d)\\s" +
                "({" + COLUMN_NAME.bytes + "}\\d+|\\-)";
    }

    public static ApacheWebAccessLogParser getInstance() {
        if (instance == null) {
            instance = new ApacheWebAccessLogParser();
        }
        return instance;
    }

    @Override
    public Matcher matchRegex(String input) {
        if (regex.length() == 0) {
            System.out.println("Length of regex cannot be 0!");
            return null;
        }
        String trimmed_input = input.trim();
        Pattern pattern = new Pattern(regex);
        Matcher matcher = pattern.matcher(trimmed_input);
        return matcher;
        /*
        while (matchIterator.hasMore()) {
            System.out.println("-------------------------------------");
            MatchResult matchResult = matchIterator.nextMatch();
            int gc = matchResult.groupCount();
            System.out.println("Group Count :" + gc);
            for (COLUMN_NAME column_name : COLUMN_NAME.values()) {
                System.out.println("Group '" + column_name + "' : " + matchResult.group(column_name.toString()));
            }
        }
        */
    }

    @Override
    public String getProcessorType() {
        return "Apache Web Access Log Parser";
    }

    @Override
    public String packageMatchingResult(Matcher matcher) {
        MatchIterator matchIterator = matcher.findAll();
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

    enum COLUMN_NAME {
        ip_address, username, user_id, day, month, year, hour, minute, second, time_zone, method, url, protocol, status_code, bytes
    }
}
