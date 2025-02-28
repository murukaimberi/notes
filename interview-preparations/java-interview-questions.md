# Java interview questions

---

## Basic Syntax
* Describe Java in a single sentence
  * Java is a platform-independent (WORA) object-oriented language with automatic memory management (garbage collection), strong typing, and a rich standard library.
* What are the differences between primitive data type and objects in Java?
  * primitives store actual values while objects store references.
  * primitives take up considerable less memory than objects
  * primitives have limited built in methods while objects allow you to implement as many methods as possible.
  * primitives can not be null while objects can be.
  * primitives are generally faster to access and manipulate.
* What is the difference between String, StringBuilder and StringBuffer?
  * String is immutable and best for single threaded environments
  * StringBuilder allows for creating of mutable strings though it is also best for single threaded situations.
  * StringBuffer is best when you want to use it for multithreaded environments since it is synchronous and thread safe.
* What is the purpose of the static keyword in Java?
  * The static keyword helps to declare class level members of the class.
  * This allows for sharing data across all instances of the class.
* What is the difference between == and .equals() when comparing strings?
  * == compares object references (memory addresses), while .equals() compares the content of strings.
* What is method overloading in Java?
  * it is a powerful technique that allows a class to have multiple methods with the same name but different parameters.
* How do you read user input from the console in Java?
  * ```Scanner scanner = new Scanner(System.in); String input = scanner.nextLine();```
* What is the difference between ArrayList and array?
  * ArrayList allows for extensible list though it does not accommodate primitive types.
* Explain the difference between Runnable and Callable interfaces in Java concurrency?
  * both are used for defining tasks that can be executed by threads.
  * Runnable doesn't return a result, can not throw checked exceptions, and thus, is simpler to use for basic tasks.
  * Callable can return a result and can throw checked exceptions.
  * Callable is typically used with ExecutorService for asynchronous task execution.
* What is the difference between fail-fast and fail-safe iterators?
  * Fail-fast iterators throw `ConcurrentModificationException` if the collection is modified while iterating.
  * Examples include iterators of `ArrayList` and `HashMap`.
  * Fail-safe do not throw exceptions if the collection is modified; they work on a clone of the collection.
  * Examples include iterators of `ConcurrentHashMap` and `CopyOnWrteArrayList`.
  * Fail-fast : 
    * Provide immediate feedback about concurrent modifications.
    * Help detect programming errors early in the development process.
    * Ensure data consistency by preventing iteration over a collection that has been modified.
  * Fail-safe : 
    * Allow for concurrent modification of the collection during iteration.
    * Provide a consistent view of the collection at the time the iterator was created.
    * Useful in scenarios where you need to iterate over a collection while allowing modifications by other threads.
* Explain the concept of Java Memory Model and how it relates to multi-threading?
  * The Java Memory Model (JMM) sets the rules for how Java programs interact with computer memory, especially in multi-threaded environments.
  * The first rule is ensuring changes to shared data are seen by all threads.
  * Another rule guarantees certain operations are indivisible (atomicity)
  * The model defines a sequence of memory operations.
* 