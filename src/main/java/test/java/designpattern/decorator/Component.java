package test.java.designpattern.decorator;


/**
 *  原始接口,装饰模式,实际当中如果需要对此接口中的方法添加额外处理,就对此接口使用装饰模式,
 *  扩展接口中的方法实现, 取代继承的方式实现方法的扩展
 */
public interface Component {
	
	void show();
}
