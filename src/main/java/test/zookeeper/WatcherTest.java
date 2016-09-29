package test.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class WatcherTest implements Watcher {
	static ZooKeeper zk;
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		WatcherTest w = new WatcherTest();
		zk = new ZooKeeper("192.168.142.128:2182,192.168.142.128:2181,192.168.142.128:2183", 5000, w);
		while(true){
			Thread.sleep(1000);
			Stat exists = zk.exists("/ephemeralTest/ep1", true);
			System.out.println(exists);
		}
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("收到事件通知：" + event.toString());
		if (KeeperState.SyncConnected == event.getState()) {
		}
	}

}
