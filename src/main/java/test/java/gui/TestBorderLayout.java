package test.java.gui;

import java.awt.*;

public class TestBorderLayout {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setLayout(new BorderLayout());
		Button b1 = new Button("b1123123");
		Button b2= new Button("b2123123");
		Button b3 = new Button("b33333");
		Button b4 = new Button("b4444");
		Button b5 = new Button("b66666");
		f.setVisible(true);
		f.add(b1,BorderLayout.NORTH);
		f.add(b2,BorderLayout.CENTER);
		f.add(b3,BorderLayout.WEST);
		f.add(b4,BorderLayout.EAST);
		f.add(b5,BorderLayout.SOUTH);
		f.setSize(300,300);
	}

}
