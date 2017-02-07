import java.util.Scanner;
import java.io.*;
import static java.lang.Math.*;



public class MyProg {
    public static void main (String [] args) {
        Matrix ar1 = new Matrix(3, 4);
        Matrix ar2 = new Matrix(3, 4);

        ar1.read("1.txt");
        ar1.output();
        ar2.read("2.txt");
        ar2.output();

        try{Matrix arSum = ar1.add(ar2);
        arSum.output();
        arSum.write("3.txt");}
         catch (Exception e) {
            System.out.println(e.getMessage());//"Всевозможные эксепшны". FileNotFound и???
        }

        //System.out.println("Equal: " + ar2.equal(arSum));
        System.out.println("Equal: " + ar1.equal(ar2));




        Vector vector = new Vector(6);

        vector.read("4.txt");
        vector.output();
        System.out.println(vector.length());
    }
}

class Matrix {
    public Matrix() {}

    public Matrix(int nn) {
        n = nn;
        ar = new int[nn][nn];
    }

    public Matrix(int nn, int mm) {
        n = nn;
        m = mm;
        ar = new int[nn][mm];
    }

    public void output() {
        System.out.println("Matrix " + n + "*" + m);
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                System.out.print("ar[" + i + "][" + j + "]=" + ar[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix mA) throws Exception {
        Matrix mC = new Matrix(this.n, this.m);
        if ((this.n == mA.n) && (this.m == mA.m)) {
            for (int i = 0; i < ar.length; i++)
                for (int j = 0; j < ar[i].length; j++) {
                    mC.ar[i][j] = this.ar[i][j] + mA.ar[i][j];
                }
        } else throw new Exception("Error: Different rangs!");
        return mC;
    }

    public void read(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int nn = scanner.nextInt();
            int mm = scanner.nextInt();
            this.ar = new int[nn][mm];
            this.n = nn;
            this.m = mm;

            for (int i = 0; i < ar.length; i++)
                for (int j = 0; j < ar[i].length; j++) {
                    if (scanner.hasNext())
                        ar[i][j] = scanner.nextInt();
                }

            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());//"Всевозможные эксепшны". FileNotFound и???
        }

    }

    public void write(String filename) {
        try(PrintStream fos = new PrintStream(new FileOutputStream(filename))) {
            fos.println(n + "*" + m);
            for (int i = 0; i < ar.length; i++) {
                for (int j = 0; j < ar[i].length; j++) {
                    fos.print(ar[i][j] + " ");
                }
                fos.println();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());//"Всевозможные эксепшны". FileNotFound и???
        }
    }

    public boolean equal(Matrix mB) {
        if ((this.n == mB.n) && (this.m == mB.m)) {
            for (int i = 0; i < ar.length; i++)
                for (int j = 0; j < ar[i].length; j++) {
                    if (mB.ar[i][j] != this.ar[i][j])
                        return false;
                }
        } else return false;
        return true;
    }

    int m = 0, n = 0;
    int[][] ar;
}

class Vector extends Matrix {
    public Vector(int n) {
        super(n, 1);
    }

    public double length() {
        double mLength = 0;
        for (int i = 0; i < ar.length; i++)
            mLength = mLength + this.ar[i][0] * this.ar[i][0];
        return sqrt(mLength);
    }
}
