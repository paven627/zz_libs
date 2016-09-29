package test.java.gui.paint;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestKey {
	public static void main(String[] args) {
		new KeyFrame().launch();
	}
}
class KeyFrame extends Frame {
	public void launch() {
		setSize(200,200);
		setLocation(300,300);
		setVisible(true);
		addKeyListener(new KeyMonitro());
	}
	class KeyMonitro extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e){
			int keyCode = e.getKeyCode(); 
			if(keyCode == KeyEvent.VK_UP){
				"123".charAt(5);				
			}
		}
	}
	
}
