package test.spring.springhibernate.com.test.dao;
import java.util.List;

import test.spring.springhibernate.com.test.model.User;


public interface UserDao {
	public void save(User user);

	List<User> getUser(List<String> ids);
	
	User getUserById(int id);
}
