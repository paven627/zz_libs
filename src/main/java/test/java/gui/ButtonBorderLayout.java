package test.java.gui;
import java.awt.*;

public class ButtonBorderLayout {
	public static void main(String[] args) {
		Frame f = new Frame("aFrame");
		f.setLayout(new GridLayout(2,1));
		f.setSize(400,400);
		f.setLocation(200,200);
		f.setBackground(new Color(85,91,205));
		Panel pt = new Panel(new BorderLayout());
		Panel pb = new Panel(new BorderLayout());
		pt.setVisible(true);
		pb.setVisible(true);
		Button b1 = new Button("button1");
		Button b2 = new Button("button2");
		Button b3 = new Button("button3");
		Button b4 = new Button("button4");
		Button b5 = new Button("button5");
		Button b6 = new Button("button6");
		Button b7 = new Button("button7");
		Button b8 = new Button("button8");
		
		Panel ptt = new Panel(new GridLayout(2,1));
		Panel ptb = new Panel(new GridLayout(2,1));
		ptt.setBackground(Color.cyan);
		ptb.setBackground(Color.gray);

		pt.add(b1,BorderLayout.WEST);
		pt.add(b2,BorderLayout.EAST);
		
		ptt.add(b3);
		ptt.add(b4);
		
		pb.add(ptb,BorderLayout.CENTER);
		pt.add(ptt,BorderLayout.CENTER);
		
		pb.add(b5,BorderLayout.WEST);
		pb.add(b6,BorderLayout.EAST);
		ptb.add(b7);
		ptb.add(b8);
		
		f.add(pt);
		f.add(pb);
		f.setVisible(true);
	}
}
