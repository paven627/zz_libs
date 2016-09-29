package test.java.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestInnerClassActionEvent {
	public static void main(String[] args) {
		new TFFrame().lanuch();
	}
}

class TFFrame extends Frame {
	TextField num1, num2, num3;

	public void lanuch() {
		num1 = new TextField(10);
		num2 = new TextField(10);
		num3 = new TextField(15);
		Label lblPlus = new Label("+");
		Button btnEqual = new Button("=");
		btnEqual.addActionListener(new MyMonitor());
		setLayout(new FlowLayout());
		add(num1);
		add(lblPlus);
		add(num2);
		add(btnEqual);
		add(num3);
		pack();
		setVisible(true);
	}

	class MyMonitor implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int n1 = Integer.parseInt(num1.getText());
			int n2 = Integer.parseInt(num2.getText());
			num3.setText(""+(n1+n2));
		}

	}
}