package test.java.gui;
import java.awt.*;
import java.awt.event.*;

public class TestActionEvents {
	public static void main(String[] args) {
		Frame f = new Frame();
		Button b1 = new Button("start");
		Button b2 = new Button("stop");
		Monitor m = new Monitor();
		b1.addActionListener(m);
		b2.addActionListener(m);
	//	b2.setActionCommand("game over");
		f.add(b1,"North");
		f.add(b2,"Center");
		f.setVisible(true);
	}

}
class Monitor implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("a button has been pressed,the relative info is :\n"+
				e.getActionCommand());
	}
	
}