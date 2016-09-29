package test.java.designpattern.factory.abstractfact.Factory;

import test.java.designpattern.factory.abstractfact.abspro.IPro1;
import test.java.designpattern.factory.abstractfact.abspro.IPro2;
import test.java.designpattern.factory.abstractfact.product2.Pro1;
import test.java.designpattern.factory.abstractfact.product2.Pro2;

public class TwoFactory extends AbstractFactory {

	@Override
	public IPro1 pro1() {
		return new Pro1();
	}

	@Override
	public IPro2 pro2() {
		return new Pro2();
	}


}
