package test.java.concurrent.threadlocal;

import java.util.Random;

public class Connection {
	private int num = new Random().nextInt();
	private String username;
	private String password;


	@Override
	public String toString() {
		return "Connection [num=" + num + ", username=" + username
				+ ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
