public class MyProg1
{ public static void main(String[] args)
   { 
	int a,b,d,temp;
	a=7;
	b=5;
	d=3;
	if (b<a)
		{temp=a;
		a=b;
		b=temp;}
	if (d<a)
		{temp=a;
		a=d;
		d=temp;}
	if (d<b)
		{temp=d;
		d=b;
		b=temp;}

	System.out.println(a+"\t"+b+"\t"+d);
   }
}