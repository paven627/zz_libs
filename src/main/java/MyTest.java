import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.moji.launchserver.thirdapi.entity.CollectMatEntity;

public class MyTest {
	static Map<String, Set<CollectMatEntity>> map = new HashMap<>();


		public static void main(String[] args) {

			Set<CollectMatEntity> set = new HashSet<>();
			for (int i = 0; i < 10; i++) {
				CollectMatEntity c = new CollectMatEntity();
				c.setAdverting_id(100000013);
				c.setMatTitle("aaaa");
				c.setDescription("aaaa");
				c.setImgurl("http://www.baidu.com/a.jpg"+i);
				set.add(c);
			}
			System.out.println(set.size());
			map.put("13", set);
			
			
			System.out.println(map.hashCode());
			long l1 = System.currentTimeMillis();
			Map<String, Set<CollectMatEntity>> m = map;
			System.out.println(m.hashCode());
			
			map = new HashMap<>();
			long l2 = System.currentTimeMillis();
			System.out.println(l2 - l1);
			System.out.println(m.size());
//			map =new HashMap<>();
//			
//			CollectMatEntity c1 = new CollectMatEntity();
//			c1.setAdverting_id(100000014);
//			c1.setMatTitle("bbbb");
//			c1.setDescription("bbbbbbb");
//			c1.setImgurl("http://www.baidu.com/b.jpg");
//
//			set = map.get("13");
//			set = new HashSet<>();
//			set.add(c1);
//			map.put("13", set);
//			System.out.println(map);
//			System.out.println(m);
	}
}