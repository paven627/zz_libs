package test.java.gui;
import java.awt.*;

/**
 * GridLayout ���ð�ť��������
 * pack() ��������Frame �Ĵ�С��ð������а�ť
 * new GridLayout(rows,columns)
 */
public class TestGridLayout {
	public static void main(String[] args) {
		Frame f = new Frame();
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		Button b4 = new Button();
		Button b5 = new Button();
		f.setLayout(new GridLayout(3,2));
		f.setVisible(true);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		//f.setSize(500,500);
	//	f.pack();
	}

}
