package org.herring.agent.parser;

import jregex.MatchIterator;
import jregex.MatchResult;
import jregex.Matcher;
import jregex.Pattern;

/**
 * Apache Web Access Log 파서
 * User: hyunje
 * Date: 13. 5. 14.
 * Time: 오전 10:43
 * To change this template use File | Settings | File Templates.
 */
public class ApacheWebAccessLogParser extends AbstractParser {
    enum COLUMN_NAME {
        ip_address, username, user_id, day, month, year, hour, minute, second, time_zone, method, url, protocol, status_code, bytes
    }
    public ApacheWebAccessLogParser(){
        regex = "({"+COLUMN_NAME.ip_address+"}\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s" +
                "({"+COLUMN_NAME.username+"}.+?)\\s" +
                "({"+COLUMN_NAME.user_id+"}\\d+?|\\-)\\s\\[" +
                "({"+COLUMN_NAME.day+"}\\d\\d)/" +
                "({"+COLUMN_NAME.month+"}\\w\\w\\w)/" +
                "({"+COLUMN_NAME.year+"}\\d\\d\\d\\d):" +
                "({"+COLUMN_NAME.hour+"}\\d\\d):" +
                "({"+COLUMN_NAME.minute+"}\\d\\d):" +
                "({"+COLUMN_NAME.second+"}\\d\\d)\\s" +
                "({"+COLUMN_NAME.time_zone+"}[-\\+]\\d\\d\\d\\d)\\]\\s\"" +
                "({"+COLUMN_NAME.method+"}GET|POST|PUT|DELETE|HEAD|TRACE|OPTION|CONNECT)\\s" +
                "({"+COLUMN_NAME.url+"}.+?)\\s" +
                "({"+COLUMN_NAME.protocol+"}.+?)\"\\s" +
                "({"+COLUMN_NAME.status_code+"}\\d\\d\\d)\\s" +
                "({"+COLUMN_NAME.bytes+"}\\d+|\\-)";
    }
    @Override
    public void parse(String input) {
        if(regex.length() == 0){
            System.out.println("Length of regex cannot be 0!");
            return;
        }
        String trimmed_input = input.trim();
        System.out.println("-------------------------------------");
        System.out.println("Input : "+trimmed_input);
        Pattern pattern = new Pattern(regex);
        Matcher matcher = pattern.matcher(trimmed_input);
        MatchIterator matchIterator = matcher.findAll();
        while (matchIterator.hasMore()){
            System.out.println("-------------------------------------");
            MatchResult matchResult = matchIterator.nextMatch();
            int gc = matchResult.groupCount();
            System.out.println("Group Count :"+gc);
            for(COLUMN_NAME column_name : COLUMN_NAME.values()){
                System.out.println("Group '"+column_name+"' : "+matchResult.group(column_name.toString()));
            }
        }

    }
}
