package org.herring.agent.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IIS Log 파싱을 위한 Parser
 * User: hyunje
 * Date: 13. 5. 12.
 * Time: 오후 10:04
 */
public class IISLogParser extends AbstractParser{

    public IISLogParser(){
        regex = "(\\d{4}-\\d\\d-\\d\\d) (\\d\\d:\\d\\d:\\d\\d) ([a-zA-Z0-9_-]+) (\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}) (GET|POST|PUT|DELETE|HEAD|TRACE|OPTION|CONNECT) (.*?) (.*?) (\\d{1,5}) (.*?) (\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}) (.*?) (\\d{3}) (\\d{1,3}) (\\d{1,3})";

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
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmed_input);
        while(matcher.find())
        {
            System.out.println("===========================");
            System.out.println("Found Pattern!!");
            System.out.println("Founded Line : "+trimmed_input);
            for(int i=0;i<matcher.groupCount();i++){
                System.out.println("group "+i+" : "+matcher.group(i));
            }
            System.out.println("===========================");
        }
        System.out.println("-------------------------------------");
    }
}

