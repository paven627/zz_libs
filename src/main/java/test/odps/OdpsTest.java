package test.odps;

import com.aliyun.odps.Odps;
import com.aliyun.odps.Table;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;

public class OdpsTest {
	public static void main(String[] args) {
		Account account = new AliyunAccount("ahwBvJLBbXm0pJzL", "eKZOUrAcL8VU7QPS32JGuhZew3hS8I");
		Odps odps = new Odps(account);
		String odpsUrl = "http://service.odps.aliyun.com/api";
		odps.setEndpoint(odpsUrl);
		odps.setDefaultProject("moji_ad_pj");
		for (Table t : odps.tables()) {
			System.out.println(t.getName());
		}
	}
}
