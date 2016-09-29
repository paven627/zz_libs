package test.java.gui.paint;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *   ������,��������ĵط���һ��ԲȦ
 */
public class MyMouseAdapter {
	public static void main(String[] args) {
		new MyFrame("12312313");
	}
}
class MyFrame extends Frame {
	ArrayList<Point> points = null;
	MyFrame(String s ) {
		super(s);
		points = new ArrayList<Point>();
		setLayout(null);
		setBounds(300,300,400,300);
		this.setBackground(Color.lightGray);
		setVisible(true);
		this.addMouseListener(new Monitor());
		
	}
	@Override
	public void paint(Graphics g) {
		Iterator<Point> i = points.iterator();
		while(i.hasNext()) {
			Point p =(Point)i.next();
			g.setColor(Color.BLUE);
			g.fillOval(p.x, p.y, 10, 10);
		}
	}
	public void AddPoint(Point p) {
		points.add(p);
		System.out.println(points.size());
	}
}
/**
 * MouseAdapter ��ʵ���� MouseListener �ӿ�
 * MouseListener �ӿڶ�������궯�����������
 * ������ʵ��MouseListener�ӿڵĻ��ͱ���ʵ�����е�5������
 * ��̳�MouseAdapter��Ϳ�����ѡ��ĸ��Ƿ���
 */
class Monitor extends MouseAdapter {
//	@Override
	public void mousePressed(MouseEvent e) {
		MyFrame f =(MyFrame)e.getSource();
		f.AddPoint(new Point(e.getX(),e.getY()));
		f.repaint();
	}
}
