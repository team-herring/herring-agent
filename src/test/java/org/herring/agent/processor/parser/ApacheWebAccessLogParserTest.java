package org.herring.agent.processor.parser;

import jregex.MatchIterator;
import jregex.MatchResult;
import jregex.Matcher;
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

@Deprecated
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApacheWebAccessLogParserTest {
    static ApacheWebAccessLogGeneratorToString generator;
    static Matcher matcher;

    @BeforeClass
    public static void setUp() {
        generator = new ApacheWebAccessLogGeneratorToString();
    }

    @Test
    public void test0GetProcessorType() throws Exception {
        System.out.println("test0GetProcessorType");
        ApacheWebAccessLogParser parser = ApacheWebAccessLogParser.getInstance();
//        Assert.assertTrue(parser.getProcessorType() != null);
    }

    @Test
    public void test1MatchRegex() throws Exception {
        System.out.println("test1MatchRegex");
        ApacheWebAccessLogParser parser = ApacheWebAccessLogParser.getInstance();
        String sampleLog = generator.getSampleLog(30);
        System.out.println("Sample Log : \n"+sampleLog);
        matcher = parser.matchRegex(sampleLog);
        MatchIterator iterator = matcher.findAll();
        Assert.assertTrue(!(iterator == null));
        System.out.println("Matched Result");
        System.out.println("===================");
        while (iterator.hasMore()) {
            System.out.println("-------------------------------------");
            MatchResult matchResult = iterator.nextMatch();
            int gc = matchResult.groupCount();
            System.out.println("Group Count :" + gc);
            for (ApacheWebAccessLogParser.COLUMN_NAME column_name : ApacheWebAccessLogParser.COLUMN_NAME.values()) {
                System.out.println("Group '" + column_name + "' : " + matchResult.group(column_name.toString()));
            }
        }
        System.out.println("===================");
    }

    @Test
    public void test2PackageMatchingResult() throws Exception {
        System.out.println("test2PackageMatchingResult");
        ApacheWebAccessLogParser parser = ApacheWebAccessLogParser.getInstance();
        String sampleLog = generator.getSampleLog(30);
        matcher = parser.matchRegex(sampleLog);
//        String convertedString = parser.packageMatchingResult(matcher);
        System.out.println("Converted String");
        System.out.println("===================");
//        System.out.println(convertedString);
        System.out.println("===================");
    }
}
