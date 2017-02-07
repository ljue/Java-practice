import javax.swing.*;
public class MyProg3
{
    public static void main(String args[])
      { 

Employee[] mas={new Employee("Lara","manager",5000),
new Employee("Paul","IT"),
new Employee("Katy","secretary"),
new Employee("Ivan"),
new Employee("Zara","worker"),
new Employee("Fuad","IT",7000),
new Employee("Fabio","cleaner"),
new Employee("Dmitriy","boss",10000),
new Employee("Ursula"),
new Employee("Jane")};

	Employee.printMas(mas);
	System.out.println("Position with max salary:"+Employee.MaxSalaryPosition(mas));
	System.out.println("SumSal="+Employee.SumSalary(mas));
      }
}	


class Employee                   
{
    public Employee(String n, String p,double s) 
    { name=n;
     position=p;
     salary=s;
     id=nextId;
     nextId++;}

    public Employee(String n) 
    { this(n,"worker",5000);
     id=nextId;
     nextId++;}

    public Employee(String n, String p) 
    { //this(n,p
     name=n;
     position=p;
     if(p.equals("worker"))
     {salary=5000;}
     else
     {salary=6000;}
     id=nextId;
     nextId++;}

    public String getName()
    {return name;}

    public String getPosition()
    {return position;}

    public double getSalary()
    {return salary;}

    public int getId()
    {return id;}

    public void printEverything()
    {
        System.out.println(name+"  "+position+"  "+salary+"\n");
    }

    public static void printMas(Employee[] mas)
    {
	for (int i=0;i<=mas.length-1;i++)
        System.out.println(mas[i].name+"  "+mas[i].position+"  "+mas[i].salary+"\n");
    }


   private static Employee maxSalary(Employee a, Employee b)
    { 
       if (a.salary<b.salary)
          return b;
       else 
          return a;
    }

    public static String MaxSalaryPosition(Employee arr[])
    {
        Employee maxSal=arr[0];
        for (int i=1;i<=arr.length-1;i++)
            maxSal=maxSalary(maxSal, arr[i]);
        return maxSal.position;
    }

public static double SumSalary(Employee arr[])
    {
        double sum=arr[0].salary;
        for (int i=1;i<=arr.length-1;i++)
            sum=sum+arr[i].salary;
        return sum;
    }
    
    private String name;
    private String position;
    private double salary;
    private int id;
    private static int nextId=0;
}

