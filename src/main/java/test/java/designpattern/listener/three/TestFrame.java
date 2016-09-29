package test.java.designpattern.listener.three;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame extends Frame {
	public void launch(){
		Button b = new Button("click");
		b.addActionListener(new MyActionListener());
		b.addActionListener(new MyActionListener2());
		add(b);
		pack();
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestFrame().launch();
	}
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Button pressed");
		}
	}
	
	private class MyActionListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Button pressed2");
		}
	}
	
}
