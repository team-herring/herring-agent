package org.herring.agent.processor.parser;

import jregex.MatchIterator;
import jregex.MatchResult;
import jregex.Matcher;
import jregex.Pattern;
import org.herring.agent.util.AgentConfiguration;

import java.util.*;

/**
 * Apache Web Access Log 파서
 * User: hyunje
 * Date: 13. 5. 14.
 * Time: 오전 10:43
 *
 * TODO: Append Herring_timestamp
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
        return pattern.matcher(trimmed_input);
    }

    @Override
    public List<Map<String,String>> packageMatchingResult(Matcher matcher) {
        MatchIterator matchIterator = matcher.findAll();

        List<Map<String,String>> resultMapList = new ArrayList<Map<String, String>>();

        while (matchIterator.hasMore()){
            MatchResult matchResult = matchIterator.nextMatch();
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

            Map<String,String> aLogResult = new HashMap<String, String>();

            for(COLUMN_NAME column_name : COLUMN_NAME.values()){
                String col_name = column_name.toString();
                String col_data = matchResult.group(col_name);
                aLogResult.put(col_name,col_data);
            }
            calendar.set(Integer.parseInt(matchResult.group(COLUMN_NAME.year.toString())),
                    Integer.parseInt(matchResult.group(COLUMN_NAME.month.toString())),
                    Integer.parseInt(matchResult.group(COLUMN_NAME.day.toString())),
                    Integer.parseInt(matchResult.group(COLUMN_NAME.hour.toString())),
                    Integer.parseInt(matchResult.group(COLUMN_NAME.minute.toString())),
                    Integer.parseInt(matchResult.group(COLUMN_NAME.second.toString())));
            aLogResult.put("herring_timestamp", String.valueOf(calendar.getTimeInMillis()));
            resultMapList.add(aLogResult);
        }
        return resultMapList;
    }

    enum COLUMN_NAME {
        ip_address, username, user_id, day, month, year, hour, minute, second, time_zone, method, url, protocol, status_code, bytes
    }
}
