package test.java.designpattern.state;

public class HappyState extends MMState {

	@Override
	public void cry() {
		System.out.println("Happy Cry");
	}

	@Override
	public void say() {
		System.out.println("happy say");
	}

	@Override
	public void smile() {
		System.out.println("happy smile");
	}
	
}
