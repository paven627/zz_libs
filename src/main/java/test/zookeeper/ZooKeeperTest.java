package test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperTest implements Watcher {

	private static final int SEESSION_TIMEOUT = 10000;
	private static final String CONNECTION_STRING = "10.200.4.141:2181";
	private static final String ZK_PATH = "/configTest";
	private ZooKeeper zk = null;

	private CountDownLatch countDownLatch = new CountDownLatch(1);

	/**
	 * @description 创建zookeeper连接
	 * @param
	 * @return
	 * @throws
	 */
	public void createConnection(String connectString, int sessionTimeout) {
		this.releaseConnection();
		try {
			zk = new ZooKeeper(connectString, sessionTimeout, this);
			countDownLatch.await();
		} catch (InterruptedException e) {
			System.out.println("连接创建失败，发生InterruptedException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("连接创建失败，发生IOException");
			e.printStackTrace();
		}

	}

	/**
	 * @description 释放zookeeper连接
	 * @param
	 * @return
	 * @throws
	 */
	public void releaseConnection() {
		if (null != zk) {
			try {
				this.zk.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @description 创建节点
	 * @param
	 * @return
	 * @throws
	 */
	public boolean createPath(String path, String data) {
		try {
			if (null == this.zk.exists(path, true)) {
				System.out.println("节点创建成功,Path："
						+ this.zk.create(path, data.getBytes(),
								Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
			}

		} catch (KeeperException e) {
			System.out.println("节点创建失败 ，发生KeeperException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("节点创建失败 ，发生InterruptedException");
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * @description 读取节点数据内容
	 * @param
	 * @return
	 * @throws
	 */
	public String readData(String path) {
		try {
			System.out.println("获取数据成功，path:" + path);
			return new String(this.zk.getData(path, true, null));
		} catch (KeeperException e) {
			System.out.println("读取数据失败 ，发生KeeperException");
			e.printStackTrace();
			return "";
		} catch (InterruptedException e) {
			System.out.println("读取数据失败 ，发生InterruptedException");
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * @description 更新节点数据内容
	 * @param
	 * @return
	 * @throws
	 */
	public boolean wirteData(String path, String data) {
		try {
			System.out.println("更新数据成功，path：" + path + ", stat: "
					+ this.zk.setData(path, data.getBytes(), -1));
		} catch (KeeperException e) {
			System.out.println("更新数据失败 ，发生KeeperException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("更新数据失败 ，发生InterruptedException");
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * @description 刪除指定節點
	 * @param
	 * @return
	 * @throws
	 */
	public void deleteNode(String path) {

		try {
			this.zk.delete(path, -1);
			System.out.println("刪除節點 成功，path：" + path);
		} catch (KeeperException e) {
			System.out.println("刪除節點 失败 ，发生KeeperException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("刪除節點 失败 ，发生InterruptedException");
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("收到事件通知：" + event.toString() + "\n");
		if (KeeperState.SyncConnected == event.getState()) {
			countDownLatch.countDown();
		}
	}

	public static void main(String[] args) throws Exception {
		ZooKeeperTest zkTest = new ZooKeeperTest();
		zkTest.createConnection(CONNECTION_STRING, SEESSION_TIMEOUT);
		// if(zkTest.createPath(ZK_PATH, "節點初始內容"))
		// {
		// System.out.println("数据内容："+zkTest.readData(ZK_PATH));
		// zkTest.wirteData(ZK_PATH, "节点更新内容");
		// System.out.println("数据内容："+zkTest.readData(ZK_PATH));
		// zkTest.deleteNode(ZK_PATH);
		// }
		zkTest.createPath(ZK_PATH, "節點初始內容");
		zkTest.createPath("/configTest/a", "節點初始內容");
		zkTest.createPath("/configTest/b", "節點初始內容");

		System.out.println(zkTest.zk.getChildren(ZK_PATH, true));

		zkTest.releaseConnection();
	}

}