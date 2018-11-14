package test.java.designpattern.decorator;

public class Test {

	public static void main(String[] args) {
		Component com = new ComponentImpl();
		ComponentDecorator decor = new ComponentDecoratorImplA(com);
		decor.show();
	}

}
