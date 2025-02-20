## Two pointers algorithm
- It is a technique used in problem solving to efficiently traverse or manipulate sequential data structures, such as arrays or linked lists.
- It involves two pointers that traverse the data structure in a coordinated manner
- These two pointers adjust (dynamically) based on a specific condition or criteria
- Whenever there is a requirement to find two data elements in a data structure that satisfy a certain condition then the two pointers algorithm should come to mind.
- The two pointers method can be used to solve : 
    - Reverse an array
    - Pair with given sum in a sorted array
### Real-world problem
- Memory management

### When to use this pattern
- Linear data structures 
- Process pairs : process two data elements that are at two different positions simultaneously.
- Dynamic pointer movement

## Fast and Slow Pointers
- It uses two pointers to traverse an iterable data structure but at different speeds, often to identify cycles or to find a specific target.
- It is typically used to analyze the structure or properties of the data.
- The key idea is that pointers start at the same location and then start moving at different speeds.
- The slow pointer may move one step at a time while the fast pointer may move two steps at a time.
- This pattern is also called the Hare and Tortoise algorithm
- This approach enables the algorithm to detect specific properties within the data structure such as cycles, midpoints or intersections.
Examples
- Given the head of a singly linked list, return the middle node of the linked list.
Solution 
- The idea is to iterate the linked list using fast and slow pointers with slow pointer moving one step whereas the fast pointer moving two steps. When the fast pointer reaches the last node or becomes NULL the slow pointer will be pointing to the middle.

# Design Patterns
- A design pattern refers to an established and refined solution for a repetitive issue in software development.
- It helps to write maintainable, extensible and reusable software
- Design patterns are focused on fixing problems in a specific type of software design, like how objects are organized and used.
- Architectural designs help to organize how an application is built. They help in coming up with scalable applications that are secure.
Types of design patterns
1. Creational Design Patterns
2. Structural Design Patterns
3. Behavioral Design Patterns
4. J2EE Design Patterns

## Singleton Design Pattern
- It is a way to make sure we have one instance of the class per context / system.
- To implement singleton Pattern we need to : 
    - Define a class with a private constructor, so that no other class can instantiate it.
    - Declare a static variable of the same type and create an instance of the class in the variable.
    - Declare a static method that returns the instance of the class.
    - It is used for logging, driver objects, caching, and thread pooling.
    - It is also used in other design patterns like Abstract, Factory, Builder, Prototype, Facade etc
    - It is used in core java Java classes also eg java.lang.Runtime and java.awt.Desktop
### Java Singleton Initialization
To implement the singleton pattern we need : 
* Private constructor to restrict instantiation of the class from other classes
* Private static variable of the same type as the class that is the only instance of the class
* Public static method that returns the instance of the class, this is the global access point for the outer world to get the instance of the singleton class.

#### Implementation Approaches
1. Eager initialization
  * The instance of the singleton class is created at the time of class loading.
  * The drawback on this is that resources are allocated regardless they are or are not going be used.
  * ```package com.journaldev.singleton;
    public class EagerInitializedSingleton {
        // this variable is instantiated when the class is loaded and the object is provisioned and allocated.
        // this is a problem since the object is available at the start of the context / system though is may 
        // never be used in until late.
        private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

        // private constructor to avoid client applications using the constructor
        private EagerInitializedSingleton(){}
    
        // there is no exception handling here hence it becomes a challenge if there is an 
        // exception thrown.
        public static EagerInitializedSingleton getInstance() {
            return instance;
        }
    }
    ```
  * In most cases singleton classes are created for resource intensive operations hence this approach poses challenges as it should only be used in situations where the object being instantiated is not resource heavy.
  * the above implementation also does not provide proper exception handling hence it is a problem.
2. Static block initialization
   * initialization is does through a static block which allows for things like exception handling.
   * both static and eager initialization create the object even if it is not needed.
   * ```package com.journaldev.singleton;
     public class StaticBlockSingleton {
     
         private static StaticBlockSingleton instance;
     
         private StaticBlockSingleton(){}
     
         // static block initialization for exception handling
         static {
            try {
                instance = new StaticBlockSingleton();
            } catch (Exception e) {
                throw new RuntimeException("Exception occurred in creating singleton instance");
            }
         }
         public static StaticBlockSingleton getInstance() {
            return instance;
         }
     }
3. Lazy initialization
   * ```package com.journaldev.singleton;
     public class LazyInitializedSingleton {
     
         private static LazyInitializedSingleton instance;
         private LazyInitializedSingleton(){}
         // A new instance is obtained only when this method is called.
         public static LazyInitializedSingleton getInstance() {
            if (instance == null) {
                instance = new LazyInitializedSingleton();
            }
            return instance;
         }
     }
     ```
   * the preceding approach is not thread safe and hence in a multithreaded environment if multiple threads enter the if statement at the same time then we result in a non singleton situation.
4. Thread safe singleton
   * the simple way to create a thread-safe singleton class is to make the global access method synchronized so that only one thread can execute this method at a time.
   * although this approach solve the issue of concurrency this creates a problem of performance due to the cost associated with synchronized method.
   * to avoid this extra overhead every time, double-checked locking principle is used. This time the synchronised is used inside the `if` condition with an additional check to ensure that only one instance of the singleton class is created.
5. Bill Pugh Singleton implementation
   * This approach uses an inner class to achieve the goal.
   * ```
     package com.journaldev.singleton;
     public class BillPughSingleton {
         private BillPughSingleton(){}
         private static class SingletonHelper {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
         }
         public static BillPughSingleton getInstance() {
            return SingletonHelper.INSTANCE;
         }
     }
     ```
   * The private inner static class that contains the instance of the singleton class.
   * the inner class is only loaded into memory once the `getInstance()` method is called.
   * this is the most widely used method as it does not require synchronization.

## Factory design pattern
* it always for creating different types of objects, allowing the creation of the object (type) to be delegated to the caller.
* define an interface or abstract class that declares the factory method for creating objects.
* create concrete classes that implement the interface or abstract class and provide their own implementation of the factory method.

### Implementation of Factory Design Pattern
* ```
  public abstract class Computer {
    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();
    
    @Override
    public String toString() {
        return "RAM = " this.getRAM() + ", HDD = " + this.getHDD() + ", CPU = this.getCPU();
    }
  }
  ```
* The above a superclass that overrides the toString() from object. You can use an abstract super class or an interface.
* We can then have the following as a subclass that extends the abstract class that we defined above.
* ```
  public class PC extends Computer {
    private String ram;
    private String hdd;
    private String cpu;
    public PC(String ram, String hdd, String cpu){
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
    }
    @Override
    public String getRAM() {
		return this.ram;
    }
    @Override
    public String getHDD() {
		return this.hdd;
    }
    @Override
    public String getCPU() {
		return this.cpu;
    }
  }
  ```
* ```
  public class Server extends Computer {
    private String ram;
    private String hdd;
    private String cpu;
  
    public Server(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }  
  
    @Override
    public String getRAM() {
        return this.ram;
    }
  
    @Override
    public String getHDD(){
        return this.hdd;
    }
  
    @Override
    public String getCPU() {
        return this.cpu;
    }
  }
  ```
* ```
  // This is the Factory class which will be used for object creation.
  public class ComputerFactory {
    public static Computer getComputer(String type, String ram, String hdd, String cpu){
		if("PC".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
		else if("Server".equalsIgnoreCase(type)) return new Server(ram, hdd, cpu);
		
		return null;
    }
  }
  ```
* We can keep the factory class Singleton or we can keep the method that returns the subclass as static.
* Notice that based on the input parameter, different subclass is created and returned.

#### Advantages of Factory Design Pattern
1. Factory design pattern provides approach to code for interface rather than implementation
2. It makes our code more robust less coupled and easy to extend since the creation of the object is removed from the client code.
3. Factory pattern provides abstraction between implementation and client classes through inheritance.

#### Examples in JDK
* Calendar
* ResourceBundle
* NumberFormat
* the `valueOf()` in the primitive wrapper classes.

## Observer Design Pattern
* The observer pattern defines a dependency where when one object's state changes, all objects that depend on it are notified and updated automatically.

### Implementation
* Define an interface for the observer objects that satisfies the methods to be called when the state of the object changes.
* define an interface for the subject(observed) object that allows Observer objects to register, remove and notify them about changes.
* create concrete observer classes that implement the Observer interface and provide their own implementation of the update method.
* create a concrete Subject class that implements the subject interface and maintains a list of registered Observer objects.

> [!NOTE]
> Design Principle - Identify the aspects of your application that vary and separate them from what stays the same.