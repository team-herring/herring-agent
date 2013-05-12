package org.herring.agent.parser;

/**
 * Created with IntelliJ IDEA.
 * User: hyunje
 * Date: 13. 5. 12.
 * Time: 오후 10:04
 * To change this template use File | Settings | File Templates.
 */
public class IISLogParser extends AbstractParser{

    public IISLogParser(){
        regex = "(\\d{4}-\\d\\d-\\d\\d) (\\d\\d:\\d\\d:\\d\\d) ([a-zA-Z0-9_-]+) (\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}) (GET|POST|PUT|DELETE|HEAD|TRACE|OPTION|CONNECT) (.*?) (\\d{1,5}) (.*?) (\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}) (.*?) (\\d{3}) (\\d{1,3}) (\\d{1,3})";

    }
    @Override
    public void parse(String input) {
    }
}

