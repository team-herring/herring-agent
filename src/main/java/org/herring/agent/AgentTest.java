package org.herring.agent;

import java.util.ArrayList;

/**
 * Test class for agent polling a directory.
 * User: hyunje
 * Date: 13. 4. 23.
 * Time: 오후 9:39
 * To change this template use File | Settings | File Templates.
 */
public class AgentTest {
    public static void main(String[] args) {
        DirectoryWatcher directoryWatcher = new DirectoryWatcher("/Users/hyunje/IdeaProjects/Herring Agent/watching");
        System.out.println("Start Polling");
        try {
            directoryWatcher.startWatching();
        } catch (Exception e) {
            System.out.println("Exception occurred!");
        }


        ArrayList<String> strArray = new ArrayList<String>();
        strArray.add("a");
        strArray.add("b");

        for (String aStr : strArray) {
            System.out.println(aStr);
        }
    }
}
