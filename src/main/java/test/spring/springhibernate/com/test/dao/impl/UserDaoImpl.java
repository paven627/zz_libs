package test.spring.springhibernate.com.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import test.spring.springhibernate.com.test.dao.UserDao;
import test.spring.springhibernate.com.test.model.User;

@Component("user")
public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> getUser(List<String> ids) {
		ArrayList<User> users = null;
		Session session = this.sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer("from User user where user.id in (");
		for (int i = 0; i < ids.size(); i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		System.out.println(sb.toString());
		Query query = session.createQuery(sb.toString());
		for (int i = 0; i < ids.size(); i++) {
			query.setParameter(i, Integer.parseInt(ids.get(i)));
		}
		users = (ArrayList<User>) query.list();
		return users;
	}

	public void save(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	public UserDaoImpl(int daoId) {
		super();
	}

	@Override
	public User getUserById(int id) {
		User u = new User();
		u.setId(id);
		u.setName("name"+id);
		System.out.print(u);
		return u;
	}

}
