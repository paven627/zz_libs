package test.java.gui.paint;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * �رմ���
 */
public class TestWindowClose {
	public static void main(String[] args) {
		new MyFrame1("123123");
	}
}
class MyFrame1 extends Frame {
	private static final long serialVersionUID = 1L;
	MyFrame1(String s){
		super(s);
		setLayout(null);
		setBounds(300,300,400,400);
		setBackground(Color.CYAN);
		setVisible(true);
		addWindowListener((new MyWindowMonitor()));
	} 
	class MyWindowMonitor extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			System.exit(-1);
		}
	}
}
