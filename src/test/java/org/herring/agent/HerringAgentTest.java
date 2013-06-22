package org.herring.agent;

import org.herring.agent.generator.IISLogGeneratorToString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * HerringAgent 테스트 클래스
 * User: hyunje
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HerringAgentTest {
    public static HerringAgent herringAgent;

    @Before
    public void setUp() throws Exception {
        herringAgent = HerringAgent.getInstance();
        Assert.assertNotNull(herringAgent);
    }

    @Test
    public void test0LoadConfiguration() throws Exception {
        herringAgent.loadConfiguration();
    }

    @Test
    public void test1AttachWatcher() throws Exception {
        herringAgent.attachWatcher();
    }

    @Test
    public void test2AttachProcessor() throws Exception {
        herringAgent.attachProcessor();
        System.out.println("AttachProcessor Done");
    }

    @Test
    public void test3AttachParser() throws Exception {
        herringAgent.attachParser();
        System.out.println("AttachParser Done");
    }

    @Test
    public void test4AttachSender() throws Exception {
        herringAgent.attachSender();
        herringAgent.prepareConnection();
        System.out.println("AttachParser Done");
    }

    @Test
    public void test5StartWatching() throws Exception {
        herringAgent.startWatching();
        System.out.println("StartWatching Done");
    }

    @Test
    public void test6NotifyProcessor() throws Exception {
        IISLogGeneratorToString generator = new IISLogGeneratorToString();
//        herringAgent.notifyProcessor(generator.getSampleLog(10));
        System.out.println("NotifyProcessor Done");
    }

    @Test
    public void test7NotifySender() throws Exception {

    }
}
