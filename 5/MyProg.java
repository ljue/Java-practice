import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class MyProg {
	public static void main(String[] args) {

		SimpleFrame myFrame = new SimpleFrame();
	}
}

class SimpleFrame extends JFrame {
	public SimpleFrame() {
		super("Main form");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		MyMAINPanel testPanel = new MyMAINPanel();
		Container contentPane = getContentPane();
		contentPane.add(testPanel);
		setSize (330, 250);
		setVisible(true);
	}
}

class MyPanel extends JPanel {
	public MyPanel() {
		
	}

	
}
class MyMAINPanel extends JPanel implements ActionListener {
	public MyMAINPanel() {
		JMenuBar menuBar = new JMenuBar();
		spisok = new ArrData();


		file = new JMenuItem("Student Data");
		edit = new JMenuItem("Faculty Data");
		help = new JMenuItem("Print the list");
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);

		add(menuBar);
		file.addActionListener(this);
		edit.addActionListener(this);
		help.addActionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(text, 30, 150);
	}

	public void actionPerformed(ActionEvent event) {
		if (file.isArmed()) {
			StudentFrame student = new StudentFrame(spisok);
		}
		if (edit.isArmed()) {
			FacultyFrame student = new FacultyFrame(spisok);
		}
		if (help.isArmed())
			spisok.print();
		repaint();
	}

	JMenuItem file, edit, help;
	String text = "";
	ArrData spisok;

	// static ArrayList<Data> spisok= new ArrayList<>();
}

class StudentFrame extends JFrame implements ActionListener {
	public StudentFrame(ArrData spisok) {
		super("Student form");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		MyPanel studentPanel = new MyPanel();
		studentPanel.setBackground(new Color(255, 255, 255, 30));
		Container contentPane = getContentPane();
		contentPane.add(studentPanel);
		setSize (330, 250);

		setVisible(true);
		flow = new FlowLayout();
		studentPanel.setLayout(flow);

		button3 = new JButton("Close");
		button1 = new JButton("Accept");
		button2 = new JButton("Clear");
		JLabel lab1 = new JLabel("Name:");
		JLabel lab2 = new JLabel(" Age:");
		text1 = new JTextField("", 10);
		text2 = new JTextField("", 5);

		studentPanel.add(lab1);
		studentPanel.add(text1);
		studentPanel.add(lab2);
		studentPanel.add(text2);
		studentPanel.add(button1);
		studentPanel.add(button2);
		studentPanel.add(button3);

		button3.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);

		innerspisok = spisok;


		class MyPanelStudent extends JPanel {
			public MyPanelStudent() {}
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawString(message, 90, 200);

			}

		}
		MyPanelStudent st = new MyPanelStudent();
		st.setBackground(new Color(255, 255, 255, 30));

		contentPane.add(st);

	}



	public void actionPerformed (ActionEvent event) {
		Object source = event.getSource();
		if (source == button1) {
			try {
				innerspisok.mNewData("Student", text1.getText(), Integer.parseInt(text2.getText()));
				message = "ACCEPTED";
				System.out.println(message);
				text1.setText("");
				text2.setText("");
			} catch (NumberFormatException ex) {
				message = "Input integer to age";
			}
		}

		else if (source == button2) {
			text1.setText("");
			text2.setText("");
		} else if (source == button3) {
			// System.exit(0);
			setVisible(false);
			dispose();
		}

		repaint();
	}

	JButton button3;
	JButton button1, button2;
	String message = "";
	String name = "", age = "";
	JTextField text1, text2;
	ArrData innerspisok;
	FlowLayout flow;
}

class FacultyFrame extends JFrame implements ActionListener {
	public FacultyFrame(ArrData spisok) {
		super("Faculty form");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		MyPanel facultyPanel = new MyPanel();
		Container contentPane = getContentPane();
		contentPane.add(facultyPanel);
		setSize (330, 250);

		setVisible(true);

		flow = new FlowLayout();
		facultyPanel.setLayout(flow);

		JLabel lab1 = new JLabel("Name:");
		JLabel lab2 = new JLabel(" Degree:");
		text1 = new JTextField("", 10);
		text2 = new JTextField("", 8);
		button1 = new JButton("Accept");
		button2 = new JButton("Clear");
		button3 = new JButton("Close");
		facultyPanel.add(lab1);
		facultyPanel.add(text1);
		facultyPanel.add(lab2);
		facultyPanel.add(text2);
		facultyPanel.add(button1);
		facultyPanel.add(button2);
		facultyPanel.add(button3);

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);


		innerspisok = spisok;


		class MyPanelFaculty extends JPanel  {
			public MyPanelFaculty() {}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawString(message, 90, 200);
			}
		}

		MyPanelFaculty st1 = new MyPanelFaculty();
		st1.setBackground(new Color(255, 255, 255, 30));
		contentPane.add(st1);
	}

	public void actionPerformed (ActionEvent event) {
		Object source = event.getSource();
		if (source == button1) {
			innerspisok.mNewData("Faculty", text1.getText(), text2.getText());
			text1.setText("");
			text2.setText("");
			message = "ACCEPTED";
		}

		else if (source == button2) {
			text1.setText("");
			text2.setText("");
		} else if (source == button3) {
			// System.exit(0);
			setVisible(false);
			dispose();
		}
		repaint();
	}




	JButton button1,
	        button2, button3;
	JTextField text1, text2;

	String message = "";
	String name = "", degree = "";
	ArrData innerspisok;
	FlowLayout flow;
}

class Data {

	public String mname, mdegree, mstatus;
	public int mage;

	Data() {
		mstatus = "";
		mname = "";
		mdegree = "";
		mage = 0;
	}

	Data(String status, String name, String degree) {
		mstatus = status;
		mname = name;
		mdegree = degree;
		mage = 0;
	}

	Data(String status, String name, int age) {
		mstatus = status;
		mname = name;
		mdegree = "";
		mage = age;
	}

	// public void printData() {
	// 	if (mstatus.equals("Student")) {
	// 		System.out.println(mstatus + ": " + mname + ", " + mage + " years");
	// 	} else if (mstatus.equals("Faculty")) {
	// 		System.out.println(mstatus + ": " + mname + ", " + mdegree + " degree");
	// 	}
	// }

}
class ArrData {

	private ArrayList<Data> arr;
	private int index;

	ArrData() {
		arr = new ArrayList<Data>();
		index = 0;
	}

	public void mNewData(String status, String name, String degree) {
		arr.add(new Data(status, name, degree));
		index++;
	}

	public void mNewData(String status, String name, int age) {
		arr.add(new Data(status, name, age));
		index++;
	}

	public void print() {
		if (index > 0) {
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).mstatus.equals("Student"))
					System.out.println(arr.get(i).mstatus + " " + arr.get(i).mname + " " + arr.get(i).mage);
				else if (arr.get(i).mstatus.equals("Faculty")) {
					System.out.println(arr.get(i).mstatus + " " + arr.get(i).mname + " " + arr.get(i).mdegree);
					// arr[i].printData();
				}
			}
			System.out.println();
		} else {System.out.println("Array is null");};
	}
}
