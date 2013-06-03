package org.herring.agent.processor.parser;

import jregex.MatchIterator;
import org.herring.agent.generator.ApacheWebAccessLogGeneratorToString;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Apache Web Access Log Parser 테스트 클래스
 * User: hyunje
 * Date: 13. 6. 4.
 * Time: 오전 2:51
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApacheWebAccessLogParserTest {
    ApacheWebAccessLogGeneratorToString generator;
    MatchIterator iterator;

    @BeforeClass
    public void setUp() {
        generator = new ApacheWebAccessLogGeneratorToString();
        iterator = null;
    }

    @Test
    public void test0GetProcessorType() throws Exception {
        ApacheWebAccessLogParser parser = ApacheWebAccessLogParser.getInstance();
        Assert.assertTrue(parser.getProcessorType() != null);
    }

    @Test
    public void test1MatchRegex() throws Exception {
        ApacheWebAccessLogParser parser = ApacheWebAccessLogParser.getInstance();
        iterator = parser.matchRegex(generator.getSampleLog(30));
        Assert.assertTrue(iterator != null);
    }

    @Test
    public void test2MatchIteratorToString() throws Exception {
        ApacheWebAccessLogParser parser = ApacheWebAccessLogParser.getInstance();
        Assert.assertTrue(parser.matchIteratorToString(iterator) != null);
    }
}
