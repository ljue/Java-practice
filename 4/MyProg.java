import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
// как сделать буквы на кнопках русскими?
 public class MyProg
{
    public static void main(String[] args)
    {Toolkit kit=Toolkit.getDefaultToolkit();
     Dimension screenSize = kit.getScreenSize();//возвращает разрешение экрана
     int x=screenSize.width;
     int y=screenSize.height;
     SimpleFrame myFrame= new SimpleFrame(x/2,y/2);
    }
}

class SimpleFrame extends JFrame
{
    public SimpleFrame(int x, int y)
    {
    setTitle("This is my frame!");
     setDefaultCloseOperation(EXIT_ON_CLOSE);

     MyPanel buttonPanel=new MyPanel();
     Container contentPane= getContentPane();
     contentPane.add(buttonPanel);
     setLocation(x/2,y/2);  
     setSize(x, y);
     setVisible(true);
    }
}
 class MyPanel extends JPanel implements ActionListener
 {
     public MyPanel()
     { // creates buttons
	     
	     button1 = new JButton("Bottom");
	     button2 = new JButton("Top");
	     button3 = new JButton("Left");
	     button4 = new JButton("Right");
	     button5 = new JButton("Dark");
	     button6 = new JButton("Light");
	     // add buttons to panel
	     add(button1);
	     add(button2);
	     add(button3);
	     add(button4);
	     add(button5);
	     add(button6);

	     // associate actions with buttons
	     button1.addActionListener(this);// слушатель - MyPanel
	     button2.addActionListener(this);
	     button3.addActionListener(this);
	     button4.addActionListener(this);
	     button5.addActionListener(this);
	     button6.addActionListener(this);
     }

     // drawing text in panel
     public void paintComponent(Graphics g)
	    {super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
	     g2.drawString(text, 30,70);
	     g2.setColor(new Color(0,gr,0));
			BasicStroke pen = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 30);
			g2.setStroke(pen);
			g2.draw(sh);
     }

     public void actionPerformed (ActionEvent event)
      {Object source=event.getSource();
       if (source==button1)
           {sh = new Line2D.Double(sh.getX1(),sh.getY1()+dx,sh.getX2(),sh.getY2()+dx);
            text="The first button \"Bottom\" was pressed";}

       else if (source==button2)
           {sh = new Line2D.Double(sh.getX1(),sh.getY1()-dx,sh.getX2(),sh.getY2()-dx);
            text="The second button \"Top\" was pressed";}
        else if (source==button3)
           {sh = new Line2D.Double(sh.getX1()-dx,sh.getY1(),sh.getX2()-dx,sh.getY2());
            text="The third button \"Left\" was pressed";}

       else if (source==button4)
           {sh = new Line2D.Double(sh.getX1()+dx,sh.getY1(),sh.getX2()+dx,sh.getY2());
            text="The fourth button \"Right\" was pressed";}
        else if (source==button5)
           {if(gr>=10){
	           	gr-=10;
	            text="The fifth button \"Dark\" was pressed";}
	            else text="The fifth button \"Dark\" was pressed But this Color is the darkest";
	        }
       else if (source==button6)
           {if(gr<246){
	           	gr+=10;
	            text="The sixth button \"Light\" was pressed";}
	        else text="The fifth button \"Light\" was pressed But this Color is the lightest";
        	}
       repaint(); // No need because setBackground() is called here
      }

    JButton button1, button2, button3, button4, button5, button6;
    String text="No button was pressed";
    Line2D sh = new Line2D.Double(250, 100, 450, 300);
    int gr=128;
    int dx=20;
}


// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

//  public class MyProg
// { public static void main(String[] args)
//     { new SimpleFrame();
//     }
// }

// class SimpleFrame extends JFrame
// { public SimpleFrame()
//     {super("my interactive form!");
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
//      MyPanel testPanel=new MyPanel();
//      Container contentPane= getContentPane();
//      contentPane.add(testPanel);
//      setSize (340,170);
//      setVisible(true);
//     }
// }

//  class MyPanel extends JPanel implements ActionListener
//  { public MyPanel()
//     {JLabel lab1= new JLabel("Name:");
//      JLabel lab2= new JLabel("    Zip:");
//      text1 = new JTextField("", 10);
//      text2 = new JTextField("", 8);
//      button1 = new JButton("Ok");
//      button2 = new JButton("Clear");
//      add(lab1);
//      add(text1);
//      add(lab2);
//      add(text2);
//      add(button1);
//      add(button2);

//      button1.addActionListener(this);
//      button2.addActionListener(this);
//      }

//      public void paintComponent(Graphics g)
//     {super.paintComponent(g);
//        { g.drawString("Name: "+ name, 30,80);
//           g.drawString("Zip: "+ zip, 30,95);
//           g.drawString(message, 90,120);
//        }
//      }

//      public void actionPerformed (ActionEvent event)
//       {Object source=event.getSource();
//        if (source==button1)
//            {name=text1.getText();
//             zip=text2.getText();
//             message="The OK button was pressed";}

//        else if (source==button2)
//            {text1.setText("");
//             text2.setText("");
//             message="The CLEAR button was pressed";}

//           repaint();
//      }

//     JButton button1, button2;
//     JTextField text1, text2;
//     String message="No button was pressed";
//     String name="", zip="";
// }


// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

//  public class MyProg
// { public static void main(String[] args)
//     {new SimpleFrame();
//     }
// }

// class SimpleFrame extends JFrame
// { public SimpleFrame()
//     {super("My interactive form!");
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
//      MyPanel testPanel=new MyPanel();
//      Container contentPane= getContentPane();
//      contentPane.add(testPanel);
//      setSize (340,250);
//      setVisible(true);
//     }
// }

//  class MyPanel extends JPanel implements ActionListener
//  { public MyPanel()
//     {JLabel lab1= new JLabel("Name:");
//      JLabel lab2= new JLabel("  Password:");
//      JLabel lab3= new JLabel("  Address:");
//      text1 = new JTextField("", 10);
//      text2 = new JTextField("", 8);
//      text3 = new JTextArea(4, 20);  // 4 lines of 20 columns each
//      text3.setLineWrap(true);
//      button1 = new JButton("Ok");
//      button2 = new JButton("Clear");
//      add(lab1);
//      add(text1);
//      add(lab2);
//      add(text2);
//      add(lab3);
//      add(text3);
//      add(button1);
//      add(button2);
//      button1.addActionListener(this);
//      button2.addActionListener(this);
//      }

//      public void paintComponent(Graphics g)
//     {super.paintComponent(g);
//       g.drawString("Name: "+ name, 30,160);
//       g.drawString("Psw: "+ psw, 30,180);
//       g.drawString("Address: "+ address, 30,200);
//       g.drawString(message, 90,220);
//      }

//      public void actionPerformed (ActionEvent event)
//       {Object source=event.getSource();
//        if (source==button1)
//            {name=text1.getText();
//             psw=text2.getText();
//             address=text3.getText();
//             message="The OK button was pressed";}

//        else if (source==button2)
//            {text1.setText("");
//             text2.setText("");
//             text3.setText("");
//             message="The CLEAR button was pressed";}
//        repaint();
//      }

//     JButton button1, button2;
//     JTextField text1, text2;
//     JTextArea text3;
//     String message="No button was pressed";
//     String name="", psw="", address="";
// }
