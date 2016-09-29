package test.java.designpattern.state;

public class UnHappyState extends MMState{

	@Override
	public void cry() {
		System.out.println("unhappy cry");
	}

	@Override
	public void say() {
		System.out.println("unhappy say");
	}

	@Override
	public void smile() {
		System.out.println("unhappy smile");
	}
	
}
