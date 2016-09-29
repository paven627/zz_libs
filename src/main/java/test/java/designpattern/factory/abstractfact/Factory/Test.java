package test.java.designpattern.factory.abstractfact.Factory;

import test.java.designpattern.factory.abstractfact.abspro.IPro1;
import test.java.designpattern.factory.abstractfact.abspro.IPro2;

public class Test {
	
	
	public static void main(String[] args) {
		AbstractFactory fac = AbstractFactory.createFactory(2);
		IPro1 p1 = fac.pro1();
		IPro2 p2 = fac.pro2();
		
		p1.show();
		p2.show();
		
	}
}
