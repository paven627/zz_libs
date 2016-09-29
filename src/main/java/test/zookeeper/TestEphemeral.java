package test.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestEphemeral {
	static WatcherTest watcher = new WatcherTest();
	static ZooKeeper zk;

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		WatcherTest w = new WatcherTest();
		zk = new ZooKeeper("192.168.142.128:2182,192.168.142.128:2181,192.168.142.128:2183", 5000, w);
		Stat exists = zk.exists("/ephemeralTest", watcher);
		System.out.println(exists);
		if (exists != null) {
			zk.delete("/ephemeralTest", -1);
		}
		String create = zk.create("/ephemeralTest", "one".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(create);
		zk.create("/ephemeralTest/ep1", "临时节点1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		Thread.sleep(10000);
		zk.close();
	}

}
