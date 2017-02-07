import javax.swing.*;
public class MyProg2 {
	public static void main(String[] args) {
		String aaa, bbb, ccc;
		int a;
		aaa = JOptionPane.showInputDialog("Input an integer rang matrix");
		a = Integer.parseInt(aaa);
		int[][] mas = new int[a][];
		
		for (int i = 0; i < a; i++) {
			mas[i] = new int[i+1];
			for (int j = 0; j <= i; j++) {
				bbb = JOptionPane.showInputDialog("Input an integer");
				mas[i][j] = Integer.parseInt(bbb);
				System.out.print(mas[i][j] + "  ");
			}
			System.out.println();
		}
		int curar[]=closetoten(mas);
		System.out.print("Closer to 10: mas[" + curar[1] + "][" + curar[2] + "]=" + curar[0]);
		System.exit(0);

	}

	static int[] closetoten(int[][] ar) { // static чтоб можно было присвоить в curar
	int[] temp=new int[3];
	temp[0] = ar[0][0]; temp[1] = 0; temp[2]= 0;
	for (int i = 1; i < ar.length; i++) {
		for (int j = 0; j <= i; j++) {
			if (10 - temp[0] > 10 - ar[i][j]) {
				temp[0] = ar[i][j];
				temp[1] = i;
				temp[2] = j;
			}
		}
		System.out.println();
	}
	return temp;
}
}

