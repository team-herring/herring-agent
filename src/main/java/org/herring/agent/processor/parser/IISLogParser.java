package org.herring.agent.processor.parser;


import jregex.MatchIterator;
import jregex.MatchResult;
import jregex.Matcher;
import jregex.Pattern;
import org.herring.agent.util.AgentConfiguration;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * IIS Log 파싱을 위한 Parser
 * User: hyunje
 * Date: 13. 5. 12.
 * Time: 오후 10:04
 */
public class IISLogParser extends AbstractParser {
    private static IISLogParser instance = null;


    private IISLogParser() {
        regex = "({" + COLUMN_NAME.date + "}\\d{4}-\\d\\d-\\d\\d)\\s" +
                "({" + COLUMN_NAME.time + "}\\d\\d:\\d\\d:\\d\\d)\\s" +
                "({" + COLUMN_NAME.s_sitename + "}[a-zA-Z0-9_-]+)\\s" +
                "({" + COLUMN_NAME.s_ip + "}\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s" +
                "({" + COLUMN_NAME.cs_method + "}GET|POST|PUT|DELETE|HEAD|TRACE|OPTION|CONNECT)\\s" +
                "({" + COLUMN_NAME.cs_uri_stem + "}.*?)\\s" +
                "({" + COLUMN_NAME.cs_uri_query + "}.*?)\\s" +
                "({" + COLUMN_NAME.s_port + "}\\d{1,5})\\s" +
                "({" + COLUMN_NAME.cs_username + "}.*?)\\s" +
                "({" + COLUMN_NAME.c_ip + "}\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})\\s" +
                "({" + COLUMN_NAME.cs + "}.*?)\\s" +
                "({" + COLUMN_NAME.sc_status + "}\\d{3})\\s" +
                "({" + COLUMN_NAME.sc_substatus + "}\\d{1,3})\\s" +
                "({" + COLUMN_NAME.sc_win32_status + "}\\d{1,3})";
    }

    public static IISLogParser getInstance() {
        if (instance == null) {
            instance = new IISLogParser();
        }
        return instance;
    }

    @Override
    protected Matcher matchRegex(String input) {
        if (regex.length() == 0) {
            System.out.println("Length of regex cannot be 0!");
            return null;
        }
        String trimmed_input = input.trim();
        Pattern pattern = new Pattern(regex);
        return pattern.matcher(trimmed_input);
    }

    @Override
    protected String packageMatchingResult(Matcher matcher) {
        MatchIterator matchIterator = matcher.findAll();

        AgentConfiguration agentConfiguration = AgentConfiguration.getInstance();
        String rowDelim = agentConfiguration.rowDelimiter;
        String columnDelim = agentConfiguration.columnDelimiter;
        String dataDelim = agentConfiguration.dataDelimiter;

        StringBuilder builder = new StringBuilder();
        while (matchIterator.hasMore()){
            MatchResult matchResult = matchIterator.nextMatch();
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            int year = 0, month = 0, day =0 , hour =0 , minute =0 , second =0 ;

            for(COLUMN_NAME column_name : COLUMN_NAME.values()){
                String col_name = column_name.toString();
                String col_data = matchResult.group(col_name);
                if("date".equals(col_name)){
                    String[] strings = col_data.split("-");
                    year = Integer.parseInt(strings[0]);
                    month = Integer.parseInt(strings[1]);
                    day = Integer.parseInt(strings[2]);
                }
                if("time".equals(col_name)){
                    String[] strings = col_data.split(":");
                    hour = Integer.parseInt(strings[0]);
                    minute = Integer.parseInt(strings[1]);
                    second = Integer.parseInt(strings[2]);
                }
                builder.append(col_name).append(dataDelim).append(col_data).append(columnDelim);
            }
            calendar.set(year,month,day,hour,minute,second);
            builder.append("herring_timestamp").append(dataDelim).append(calendar.getTimeInMillis()).append(columnDelim).append(rowDelim);
        }
        return builder.toString();
    }

    enum COLUMN_NAME {
        date, time, s_sitename, s_ip, cs_method, cs_uri_stem, cs_uri_query, s_port, cs_username, c_ip, cs, sc_status, sc_substatus, sc_win32_status
    }
}

