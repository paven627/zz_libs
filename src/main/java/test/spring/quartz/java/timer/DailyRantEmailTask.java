package test.spring.quartz.java.timer;

import java.util.TimerTask;

public class DailyRantEmailTask extends TimerTask {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + ", 你好");
	}

}
