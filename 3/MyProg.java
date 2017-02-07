import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MyProg {
	public static void main(String[] args) {
	Toolkit kit=Toolkit.getDefaultToolkit();
     Dimension screenSize = kit.getScreenSize();//возвращает разрешение экрана
     int x=screenSize.width;
     int y=screenSize.height;
     DrawFrame myFrame= new DrawFrame(x/2,y/2);
	}
}

class DrawFrame extends JFrame {
	public DrawFrame(int x, int y) {
		setTitle("DrawTest");
		setSize(x, y);
		setLocation(x/2,y/2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		DrawPanel panel = new DrawPanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
		setVisible(true);
	}
}

class DrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		setBackground(Color.green);

		for (int i = 0; i < 12; i++) {
			g2.setColor(new Color((float) Math.random(),
			                      (float) Math.random(),
			                      (float) Math.random()));
			Shape sh = shape();
			BasicStroke pen = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 30);
			g2.setStroke(pen);
			g2.draw(sh);
		}
	}


	Shape shape() {
		Shape sh;
		switch ((int)(Math.random() * 3)) {
			case 0: sh = new Ellipse2D.Double(Math.random() * 60, Math.random() * 30, Math.random() * 300, Math.random() * 300);
				break;
			case 1: sh = new Rectangle2D.Double(Math.random() * 300, Math.random() * 300, Math.random() * 300, Math.random() * 300);
				break;
			default: sh = new Line2D.Double(Math.random() * 300, Math.random() * 300, Math.random() * 300, Math.random() * 300); //без default не работает
				break;
		}
		return sh;
	}
}