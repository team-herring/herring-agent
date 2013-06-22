package org.herring.agent.processor.parser;

import jregex.Matcher;

import java.util.List;
import java.util.Map;

/**
 * Parser 추상화 객체
 * User: hyunje
 */
public abstract class AbstractParser implements Parser {
    protected String regex;

    abstract Matcher matchRegex(String input);

    abstract List<Map<String,String>> packageMatchingResult(Matcher matcher);

    public List<Map<String,String>> parse(String contents) {
        Matcher matcher = this.matchRegex(contents);
//        MatchIterator matchIterator = matcher.findAll();
//        int rowCount = matchIterator.count();
        //Match Result 를 통해서 Sender에 Matching 된 결과 전송.
        //Sender에서는 Match Result를 이용해 Host에 전송.

        return this.packageMatchingResult(matcher);
    }
}
