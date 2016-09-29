package test.java.designpattern.factory.factorymethod;

public abstract class DepFactory {
	public static IApi CreateApi() {
		return new Impl2();
	}
}
