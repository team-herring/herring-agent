import org.herring.agent.watcher.PollingWatcher;

/**
 * Test class for agent polling a directory.
 * User: hyunje
 * Date: 13. 4. 23.
 * Time: 오후 9:39
 * To change this template use File | Settings | File Templates.
 */
public class AgentTest {
    public static void main(String[] args) {
        PollingWatcher pollingWatcher = new PollingWatcher("/Users/hyunje/IdeaProjects/Herring Agent/watching");
        System.out.println("Start Polling");
        try {
            pollingWatcher.startWatching();
        } catch (Exception e) {
            System.out.println("Exception occurred!");
        }
    }
}
