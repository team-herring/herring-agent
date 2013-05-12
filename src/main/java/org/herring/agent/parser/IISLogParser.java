package org.herring.agent.parser;


import jregex.MatchIterator;
import jregex.MatchResult;
import jregex.Matcher;
import jregex.Pattern;

/**
 * IIS Log 파싱을 위한 Parser
 * User: hyunje
 * Date: 13. 5. 12.
 * Time: 오후 10:04
 */
public class IISLogParser extends AbstractParser{
    enum COLUMN_NAME {
        date, time, s_sitename, s_ip, cs_method, cs_uri_stem, cs_uri_query, s_port, cs_username, c_ip, cs, sc_status, sc_substatus, sc_win32_status
    }

    public IISLogParser(){
        regex = "({"+COLUMN_NAME.date+"}\\d{4}-\\d\\d-\\d\\d)\\s" +
                "({"+COLUMN_NAME.time+"}\\d\\d:\\d\\d:\\d\\d)\\s" +
                "({"+COLUMN_NAME.s_sitename+"}[a-zA-Z0-9_-]+)\\s" +
                "({"+COLUMN_NAME.s_ip+"}\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})\\s" +
                "({"+COLUMN_NAME.cs_method+"}GET|POST|PUT|DELETE|HEAD|TRACE|OPTION|CONNECT)\\s" +
                "({"+COLUMN_NAME.cs_uri_stem+"}.*?)\\s" +
                "({"+COLUMN_NAME.cs_uri_query+"}.*?)\\s" +
                "({"+COLUMN_NAME.s_port+"}\\d{1,5})\\s" +
                "({"+COLUMN_NAME.cs_username+"}.*?)\\s" +
                "({"+COLUMN_NAME.c_ip+"}\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})\\s" +
                "({"+COLUMN_NAME.cs+"}.*?)\\s" +
                "({"+COLUMN_NAME.sc_status+"}\\d{3})\\s" +
                "({"+COLUMN_NAME.sc_substatus+"}\\d{1,3})\\s" +
                "({"+COLUMN_NAME.sc_win32_status+"}\\d{1,3})";

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
            MatchResult matchResult = matchIterator.nextMatch();
            int gc = matchResult.groupCount();
            System.out.println("Group Count :"+gc);
            for(COLUMN_NAME column_name : COLUMN_NAME.values()){
                System.out.println("Group '"+column_name+"' : "+matchResult.group(column_name.toString()));
            }
        }
    }
}

