package org.herring.agent.processor.parser;

import java.util.List;
import java.util.Map;

/**
 * Parser Interface
 * User: hyunje
 */
public interface Parser {
    List<Map<String,String>> parse(String data);
}
