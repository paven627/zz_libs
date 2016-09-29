package test.spring.ehcache.java.dao;
import java.util.List;

import test.spring.ehcache.java.model.User;


public interface UserDao {
	public void save(User user);

	List<User> getUser(List<String> ids);
	
	User getUserById(int id);
}
