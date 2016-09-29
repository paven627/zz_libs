package test.java.designpattern.factory.abstractfact.product2;

import test.java.designpattern.factory.abstractfact.abspro.IPro2;


/**
 * 实体产品
 */
public class Pro2 implements IPro2{

	@Override
	public void show() {
		System.out.println("2号工厂实体产品2");
	}

}
