package test.java.designpattern.factory.factorymethod;

public class Impl implements IApi{

	@Override
	public String t1() {
		System.out.println("实现了IAPI");
		return "1111111";
	}

}
