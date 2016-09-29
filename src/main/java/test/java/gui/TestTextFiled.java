package test.java.gui;
import java.awt.*;
import java.awt.event.*;
public class TestTextFiled {
	public static void main(String[] args) {
		Frame  f = new Frame();
		f.setVisible(true);
		TextField tf = new TextField();
		tf.addActionListener(new MyListener());
		f.add(tf);
		tf.setEchoChar('*');
		f.pack();
	}

}
class MyListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((TextField)e.getSource()).getText());
		((TextField)e.getSource()).setText("");
	}
	
}
