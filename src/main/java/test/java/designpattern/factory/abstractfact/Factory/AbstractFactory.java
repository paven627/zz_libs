package test.java.designpattern.factory.abstractfact.Factory;

import test.java.designpattern.factory.abstractfact.abspro.IPro1;
import test.java.designpattern.factory.abstractfact.abspro.IPro2;

public abstract class AbstractFactory {
	private static AbstractFactory factory = null;
	
	// 先得到工厂 ,再根据工厂产生产品
	public static AbstractFactory createFactory (int i){
		if(factory != null){
			return factory;
		}
		//根据配置文件选择
		if(i == 1){
			return new OneFactory();
		}else {
			return new TwoFactory();
		}
	}
	
	// 所有的工厂都能造的产品的方法
	public abstract IPro1 pro1();
	public abstract IPro2 pro2();
	
}
