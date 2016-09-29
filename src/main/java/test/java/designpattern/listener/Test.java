package test.java.designpattern.listener;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Child c = new Child();
		c.addListener(new Father());
		c.addListener(new Mother());
		new Thread(c).start();
	}
}

class Child implements Runnable{
	List<Listener> s = new ArrayList<Listener>();
	
	void wakeup(){
		for (Listener l : s) {
			l.lookat(new MyEvent());
		}
	}
	
	void addListener(Listener l){
		s.add(l);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.wakeup();
		
	}
}

class Father implements Listener{
	@Override
	public void lookat(MyEvent e) {	
		System.out.println("father: "+e);
	}
}
class Mother implements Listener{
	@Override
	public void lookat(MyEvent e) {
		System.out.println("mother");
	}
}

interface Listener{
	void lookat(MyEvent e);
}

class MyEvent {
	
}