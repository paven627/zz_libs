package test.java.designpattern.factory.factorymethod;

public class Client {

	
	public static void main(String[] args) {
		IApi api = DepFactory.CreateApi();
		api.t1();
	}

}
