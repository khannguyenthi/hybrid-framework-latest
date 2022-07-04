package abstraction;

public abstract class Animal {

	//Normal method
	public void eat() {
		System.out.println("I can eat at parent class");
	}
	
	//Abstract method - ko co than ham
	abstract void sleep();
}
