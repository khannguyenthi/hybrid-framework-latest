package abstraction;

public class Test extends Animal {
	
	public static void main() {
		//Abstract class ko the khoi tao
		//Animal animal = new Animal();
		
		//Interface class ko the khoi tao
		//iCar icar = new iCar();
		
		//Class bt co the khoi tao
		Normal normal = new Normal();
	}
	
	// Do extend Animal nen phai implement lai ham trong abstract Animal
	@Override
	void sleep() {}
	@Override
	public void eat() {
		System.out.println("I can eat at child class");
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.eat(); //Get ham child, neu ko co thi se goi toi ham cha
	}
	

}
