public class MyProg4
{   public static void main(String args[])
    { Bird eagle=new Bird("Oliver");
      Fish shark=new Fish("Lisa");
     
      TerreMammal dog=new TerreMammal("Sharik");
      SeaMammal whale=new SeaMammal("Bob");
     
      // Mammal cus = new Mammal ("Georg", "777-77-7777", 500);  Error! Customer is abstract!
      // Mammal cus = new TerreMammal ("Georg"); //Works!
      // cus.output();
      
      // System.out.println(worker.getName());
      // System.out.println(pupil.getName());
      eagle.output();
      System.out.println(eagle.Sound());
      System.out.println(eagle.moves());
      shark.output();
      System.out.println(shark.Sound());
      System.out.println(shark.moves());
      dog.output();
      System.out.println(dog.Sound());
      System.out.println(dog.moves());
      whale.output();
      System.out.println(whale.Sound());
      System.out.println(whale.moves());
    }
}

abstract class Animal    //  Abstract Superclass
{   public Animal(String n) 
    {name=n;
    }

    abstract public void output();
    abstract public String moves();
    abstract public String Sound();

    
    // public String getName()
    // {return name;}
    
    protected String name;
}

class Fish extends Animal
{   public Fish(String n) 
    {super(n);}

    public void output()
    {System.out.println("Fish         Name:"+name);}

    public String moves()
    {return "swim";} 

    public String Sound()
    {return "nothing";} 
}

class Bird extends Animal
{   public Bird(String n) 
    {super(n);
     }

    public void output()
    {System.out.println("Bird         Name:"+name);}

    public String moves()
    {return "fly";}

    public String Sound()
    {return "Chirp!";}
}

abstract class Mammal extends Animal
{   public Mammal(String n) 
    {super(n);
      sound="";
    }

    public String Sound()
    {return sound;}

    protected String sound;
}

class SeaMammal extends Mammal
{   public SeaMammal(String n) 
    {super(n);
      sound="Uuuuuuuu";
    }

     public void output()
     {System.out.println("SeaMammal     Name:"+name);}

     public String moves()
    {return "swim";} 
}

class TerreMammal extends Mammal
{   public TerreMammal(String n) 
    {super(n);
      sound="Rrrrrrrr";
    }

     public void output()
     {System.out.println("TerreMammal    Name:"+name);}

     public String moves()
    {return "run";} 
}
