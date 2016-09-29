package test.java.gui.paint;
import java.awt.*;
public class TestPaint {
	public static void main(String[] args) {
		new PaintFrame().launch();
	}

}

class PaintFrame extends Frame {
	public void launch(){
		setBounds(200,200,640,480);
		setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(50, 50, 80, 130);
		g.setColor(Color.green);
		g.fillRect(80, 80, 40, 40);
		g.setColor(Color.CYAN);
		g.fillArc(150, 200, 200, 200, 90, 140);
	}
}
