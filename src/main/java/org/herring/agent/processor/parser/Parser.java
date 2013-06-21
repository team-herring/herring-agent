package org.herring.agent.processor.parser;

import java.util.List;
import java.util.Map;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 22.
 * Time: 오전 2:40
 */
public interface Parser {
    List<Map<String,String>> parse(String data);
}
