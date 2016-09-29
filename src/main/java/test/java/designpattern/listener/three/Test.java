package test.java.designpattern.listener.three;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Button b = new Button();
		b.addListener(new MyActionListener());
		b.addListener(new MyActionListener2());
		b.buttonPressed();	
	}
}
class Button{
	private List<ActionListener> listeners = 
		new ArrayList<ActionListener>();
	
	public void buttonPressed() {
		ActionEvent e = new ActionEvent(
				System.currentTimeMillis(),this);
		for (int i = 0,size = listeners.size(); i < size ; i++) {
			ActionListener l = listeners.get(i);
			l.actionPerformed(e);
		}
	}
	public void addListener(ActionListener e){
		listeners.add(e);
	}
}

interface ActionListener{
	void actionPerformed(ActionEvent e);
}

class MyActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed");
	}
}
class MyActionListener2 implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed2");
	}
}


//浜嬩欢绫�琛ㄦ槑鍙戠敓鏃剁殑鐜浜嬩欢
class ActionEvent{
	long when;
	Object source ;
	
	public ActionEvent(long when, Object source){
		this.when = when;
	}
	
	public long getWhen(){
		return System.currentTimeMillis();
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}
	
}
