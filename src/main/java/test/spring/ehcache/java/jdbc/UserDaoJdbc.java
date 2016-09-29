package test.spring.ehcache.java.jdbc;

import java.util.List;

import org.springframework.stereotype.Component;

import test.spring.ehcache.java.dao.UserDao;
import test.spring.ehcache.java.model.User;

@Component("userDao")
public class UserDaoJdbc implements UserDao {

	@Override
	public List<User> getUser(List<String> ids) {
		return null;
	}

	public void save(User user) {
	}

	@Override
	public User getUserById(int id) {
		User user = new User();
		user.setId(id);
		user.setName("����"+id);
		System.out.println("��ѯ�û�" +id);
		return user;
	}

}
