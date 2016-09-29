package test.java.designpattern.factory.abstractfact.product2;

import test.java.designpattern.factory.abstractfact.abspro.IPro1;


/**
 * 实体产品
 */
public class Pro1 implements IPro1{

	@Override
	public void show() {
		System.out.println("二号工厂实体产品1");
	}

}
