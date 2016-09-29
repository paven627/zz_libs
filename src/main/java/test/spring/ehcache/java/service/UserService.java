package test.spring.ehcache.java.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import test.spring.ehcache.java.dao.UserDao;
import test.spring.ehcache.java.model.User;



@Component("userService")
public class UserService {
	
	 
	private UserDao userDao;  
	
	@Resource(name="user")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserById(int id){
		return userDao.getUserById(id);
	}
	public void init(){
		System.out.println("UserService  initial....");
	}
	
	public List<User> getUser(List<String> ids) {
		return userDao.getUser(ids);
	}
	public void destroy(){
		System.out.println("UserService  destroy...");
	}
	
	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}


	public UserService() {
		super();
	}


	public void add(User user) {
		userDao.save(user);
	}


	public UserDao getUserDao() {
		return userDao;
	}



	
}
