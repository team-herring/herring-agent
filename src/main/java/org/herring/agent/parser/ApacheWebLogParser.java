package org.herring.agent.parser;

/**
 * Line Reader for getting line in added characters.
 * User: hyunje
 * Date: 13. 5. 5.
 * Time: 오후 10:00
 */
public class ApacheWebLogParser extends AbstractParser  {

    public ApacheWebLogParser(String lines){
        this.lines = lines;
    }

    @Override
    public void parse(String input) {

    }
}
