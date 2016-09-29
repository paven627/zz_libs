package test.java.regex;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TestCodeCounter extends Frame {
	int screenWidth = this.getToolkit().getScreenSize().width;
	int screenHeight = this.getToolkit().getScreenSize().height;
	public static int WIDTH = 400;
	public static int HEIGHT = 300;
	TestCodeCounter tcc = this;
	public void launch() {
		this.setLocation((screenWidth-WIDTH)/2,(screenHeight - HEIGHT)/2);
		this.setSize(WIDTH,HEIGHT);
		setTitle("����ͳ����");
		this.setLayout(new FlowLayout());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		Button btnOpen = new Button("���ļ�");
		
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog d=new FileDialog(tcc,"FileDialog");
				d.setVisible(true);
				String filename=d.getFile();
			}
		});
		this.add(btnOpen);
		 
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestCodeCounter().launch();
	}

}
