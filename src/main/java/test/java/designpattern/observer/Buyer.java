package test.java.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Buyer implements Observer {
	String name ;
	
	public Buyer(String name) {
		super();
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(this.name+" 观察到房价更改为: " + arg);
	}
}

