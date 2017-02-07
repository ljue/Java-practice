public class MyProg {
	public static void main(String[] args) {
		Semaphore sem0 = new Semaphore(1);
		Semaphore sem1 = new Semaphore(1);
		Database myDB = new Database(sem0, sem1);
		Reader reader1 = new Reader(1, myDB);
		Reader reader2 = new Reader(2, myDB);
		Reader reader3 = new Reader(3, myDB);
		Reader reader4 = new Reader(4, myDB);
		Reader reader5 = new Reader(5, myDB);
		Writer writer1 = new Writer(1, myDB);
		Writer writer2 = new Writer(2, myDB);
		Writer writer3 = new Writer(3, myDB);
		Writer writer4 = new Writer(4, myDB);
		reader1.start();
		reader2.start();
		reader3.start();
		reader4.start();
		reader5.start();
		writer1.start();
		writer2.start();
		writer3.start();
		writer4.start();
	}
}


class Reader extends Thread {
	public Reader(int r, Database db) {
		readerNum = r;
		server = db;
	}

	public void run() {
		int c;
		//for (int i = 1; i <= 3; i++) { // It should be while (true)
			napping();
			System.err.println("reader " + readerNum + " wants to read.");

			c = server.startRead();
			System.err.println("reader " + readerNum + " is reading. Reader Count = " + c);
			Database.something();

			c = server.endRead();
		//}
	}

	public void napping() {
		try
		{sleep((int) (Math.random() * 5000)); }
		catch (InterruptedException e) {}
	}

	private Database server;
	private int readerNum;
}




class Writer extends Thread {
	public Writer(int w, Database db) {
		writerNum = w;
		server = db;
	}

	public void run() {
		// for (int i = 1; i <= 3; i++) { // It should be while (true)
			napping();
			System.err.println("writer " + writerNum + " wants to write.");
			server.startWrite();

			System.err.println("-------------- WRITER " + writerNum + " IS WRITING.");
			Database.something();

			System.err.println("               writer " + writerNum + " is done writing.");
			server.endWrite();

		//}
	}

	public void napping() {
		try
		{sleep((int) (Math.random() * 5000)); }
		catch (InterruptedException e) {}
	}

	private Database  server;
	private int writerNum;
}

class Semaphore {
	public Semaphore(int v)
	{ value = v; }

	public synchronized void P() {
		value--;
		if (value < 0)
			try
			{wait();}
			catch (InterruptedException e) {}
	}

	public synchronized void V() {
		++value;
		if (value <= 0)
			notify();
	}

	private int value;
}

class Database {
	public Database(Semaphore s, Semaphore s1) {
		readerCount = 0;

		sem1 = s1;
		sem0 = s;
	}

	public static void something() {
		try
		{Thread.sleep((int) (Math.random() * 5000)); }
		catch (InterruptedException e) {}
	}

	public synchronized int startRead() {
		// while (dbWriting)
		//   {try
		//      {wait();}
		//    catch(InterruptedException e){}
		//   }

		sem1.P();
		++readerCount;
		if (readerCount == 1) {
			sem0.P();
		}
		sem1.V();

		return readerCount;
	}

	public synchronized int endRead() {
		sem1.P();
		--readerCount;
		if (readerCount == 0)
			sem0.V();
		sem1.V();

		System.err.println("Reader is done reading. Count = " + readerCount);
		// notifyAll();
		return readerCount;
	}

	public synchronized void startWrite() {
		// while (dbReading || dbWriting) {
		// 	try
		// 	{wait();}
		// 	catch (InterruptedException e) {}
		// }
		// dbWriting = true; // the DB is being written

		sem0.P();
	}




	public synchronized void endWrite() {
		// dbWriting = false;
		// notifyAll();
		sem0.V();
	}

	private int readerCount;    // the number of active readers

	private Semaphore sem1;
	private Semaphore sem0;
}






// class Semaphore
// {
//   public Semaphore(int v)
//     { value = v; }

//   public synchronized void P()
//     { value--;
//       if (value < 0)
//         try
//           {wait();}
//         catch (InterruptedException e) {}
//     }

//   public synchronized void V()
//     { ++value;
//       if (value<=0)
//          notify();
//     }

//   private int value;
// }






// public class MyProg
// { public static void main( String args[] )
//    { Semaphore sem=new Semaphore(1);
//      Worker[] myThread = new Worker[5];
//      for (int i=0; i<5; i++)
//         myThread[i]=new Worker ("Worker"+i, sem);
//      for (int i=0; i<5; i++)
//         myThread[i].start();
//    }
// }
// class Worker extends Thread
// { private Semaphore sem;

//   public Worker(String n, Semaphore s)
//     {super(n);
//      sem=s;
//     }

//   public void run()
//     {for (int i=1; i<=10; i++)  // usually it is while (true)
//        {sem.P();
//         criticalSection();
//         sem.V();
//         nonCriticalSection();
//        }
//     }

//     public void criticalSection()
//     {System.out.println(this.getName()+"  ENTERS THE CRITICAL SECTION");
//      try
//         {Thread.sleep((int)(Math.random()*3000));}
//      catch (InterruptedException e) {}
//      System.out.println(this.getName()+"  EXITS FROM CRITICAL SECTION");
//     }

//     public void nonCriticalSection()
//     {System.out.println("     "+this.getName()+"  enters the noncritical section");
//      try
//         {Thread.sleep((int)(Math.random()*3000));}
//      catch (InterruptedException e) {}
//      System.out.println("     "+this.getName()+"  exits from the noncritical section");
//     }
// }

// class Semaphore
// {
//   public Semaphore(int v)
//     { value = v; }

//   public synchronized void P()
//     { while (value <= 0)
//         try
//           {wait();}
//         catch (InterruptedException e) {}
//       value --;
//     }

//   public synchronized void V()
//     { ++value;
//       notify();}

//   private int value;
// }










// public class MyProg
// { public static void main( String args[] )
//    { Semaphore sem0=new Semaphore(1);
//      Semaphore sem1=new Semaphore(1);
//      Worker myThread0=new Worker ("Worker0", sem0, sem1);
//      Worker myThread1=new Worker ("Worker1", sem0, sem1);
//      myThread0.start();
//      myThread1.start();
//    }
// }
// class Worker extends Thread
// { private Semaphore sem0, sem1;

//   public Worker(String n, Semaphore s0, Semaphore s1)
//     {super(n);
//      sem0=s0;
//      sem1=s1;
//     }

//   public void run()
//     {for (int i=1; i<=3; i++)
//        {int x=(int)(Math.random()*2);
//         System.err.println("x="+x);
//        if (x==0)
//            {System.err.println(this.getName()+" calls sem0.P()");
//             sem0.P();
//             criticalSection0();
//             System.err.println(this.getName()+" calls sem1.P()");
//             sem1.P();
//             criticalSection1();
//             System.err.println(this.getName()+" calls sem1.V()");
//             sem1.V();
//             System.err.println(this.getName()+" calls sem0.V()");
//             sem0.V();}
//         else
//            {System.err.println(this.getName()+" calls sem1.P()");
//             sem1.P();
//             criticalSection1();
//             System.err.println(this.getName()+" calls sem0.P()");
//             sem0.P();
//             criticalSection0();
//             System.err.println(this.getName()+" calls sem0.V()");
//             sem0.V();
//             System.err.println(this.getName()+" calls sem1.V()");
//             sem1.V();}
//        }
//       System.err.println( this.getName()+": THE END");
//     }

//     public void criticalSection0()
//     {System.err.println(this.getName()+"  ENTERS THE CRITICAL SECTION 0");
//      try
//         {Thread.sleep((int)(Math.random()*3000));}
//      catch (InterruptedException e) {}
//      System.err.println(this.getName()+"  EXITS FROM CRITICAL SECTION 0");
//     }
//     public void criticalSection1()
//     {System.err.println(this.getName()+"  ENTERS THE CRITICAL SECTION 1");
//      try
//         {Thread.sleep((int)(Math.random()*3000));}
//      catch (InterruptedException e) {}
//      System.err.println(this.getName()+"  EXITS FROM CRITICAL SECTION 1");
//     }
// }

// class Semaphore
// {
//    public Semaphore(int v)
//     { value = v; }

//   public void P()
//     { while (value <= 0);
//       value --;
//     }

//   public void V()
//     { ++value;  }

//   private int value;
// }






// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class MyProg
// { public static void main(String[] args)
//    {BoundedBuffer myBoundedBuffer = new BoundedBuffer(10);
//     new Producer(myBoundedBuffer).start();
//     new Consumer(myBoundedBuffer).start();
//    }
// }


// class BoundedBuffer
// {public int bufferSize, in, out, count;   // They are public to print the content of the BoundedBuffer easily.
//  public  int element[];                   // It is for education purposes only. Usually they are private
//  Semaphore mutex, empty, full;

//  BoundedBuffer(int mybufferSize)
//  {bufferSize=mybufferSize;
//   in=0;
//   out=0;
//   count=0;
//   element = new int[bufferSize];
//   mutex=new Semaphore(1);
//   empty=new Semaphore(bufferSize);
//   full=new Semaphore(0);
//   }

//  public void add(int x)
//     {empty.P();
//      mutex.P();
//      element[in]=x;
//      in=++in%bufferSize;
//      ++count;
//      System.out.println("Producer Entered "+x+ " Buffer size="+ count);
//      mutex.V();
//      full.V();
//     }

//  public int remove()
//     {full.P();
//      mutex.P();
//      int temporal=element[out];
//      element[out]=0;              //  Actually we don't need to do it
//      out=++out%bufferSize;
//      count--;
//      System.out.println("Consumer consumed "+temporal+ " Buffer size="+ count);
//      mutex.V();
//      empty.V();
//      return temporal;
//     }
// }

// class Semaphore
// {
//   public Semaphore(int v)
//     { value = v; }

//   public void P()
//     { while (value <= 0);
//       value --;
//     }

//   public void V()
//     { ++value;  }

//   private int value;
// }

// class Producer extends Thread
//  {Producer(BoundedBuffer b)
//    {buffer=b;}

//   public void run()
//    {int newItem;
//     for (int i=0; i<20; i++)
//        {newItem=(int)(Math.random()* 100);
//         try
//           {Thread.sleep((int)(Math.random()* 3000));}
//         catch(InterruptedException ie){}
//         buffer.add(newItem);
//        }
//     }
//   private BoundedBuffer buffer;
// }

// class Consumer extends Thread
//  {
//   Consumer(BoundedBuffer b)
//    {buffer=b;}

//   public void run()
//    {int remItem;
//     for (int i=0; i<20; i++)
//       {try
//           {Thread.sleep((int)(Math.random()* 3000));}
//        catch(InterruptedException ie){}
//        remItem=buffer.remove();
//       }
//    }
//   private BoundedBuffer buffer;
//  }







// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class MyProg
// { public static void main(String[] args)
//    {BoundedBuffer myBoundedBuffer = new BoundedBuffer(10);
//     new Producer(myBoundedBuffer).start();
//     new Consumer(myBoundedBuffer).start();
//    }
// }

// class BoundedBuffer
// {private int bufferSize, in, out, count;
//  private int element[];

//  public BoundedBuffer(int mybufferSize)
//  {bufferSize=mybufferSize;
//   in=0;
//   out=0;
//   count=0;
//   element = new int[bufferSize];
//   }

//  public synchronized void add(int x)
//     {while (count==bufferSize)
//           {try
//              {wait();}
//           catch (InterruptedException e){}
//           }
//      ++ count;
//      element[in]=x;
//      in=(in+1)%bufferSize;
//      System.err.println("New element "+x+" is added. Count="+count);
//      notify();
//     }

//  public synchronized int remove()
//     {while (count==0)
//          {try
//              {wait();}
//           catch (InterruptedException e){}
//           }
//      int temporal=element[out];
//      element[out]=0;              //  Actually we don't need to do it
//      out=++out%bufferSize;
//      count--;
//      System.err.println("            Element "+temporal+" is removed. Count="+count);
//      notify();
//      return temporal;
//     }
// }

// class Producer extends Thread
//  {public Producer(BoundedBuffer b)
//    {buffer=b;
//    }

//   public void run()
//    {int newItem;
//     while(true)
//        {newItem=(int)(Math.random()* 100);
//         try
//           {Thread.sleep((int)(Math.random()* 4000));}
//         catch(InterruptedException ie){}
//         buffer.add(newItem);
//        }
//     }
//   private BoundedBuffer buffer;
// }

// class Consumer extends Thread
//  {
//   Consumer(BoundedBuffer b)
//    {buffer=b;}

//   public void run()
//    {int remItem;
//     while(true)
//       {try
//           {Thread.sleep((int)(Math.random()* 5000));}
//        catch(InterruptedException ie){}
//        remItem=buffer.remove();
//       }
//    }
//   private BoundedBuffer buffer;
//  }
