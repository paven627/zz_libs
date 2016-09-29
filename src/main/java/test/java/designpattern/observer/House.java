package test.java.designpattern.observer;

import java.util.Observable;

/**
 * 观察者模式,几个观察者共同观察者房子的价格,一旦变动
 * 就反应到所有观察者那里
 *
 */
public class House extends Observable{
	float price ;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
		this.setChanged();
		this.notifyObservers(this);
	}

	@Override
	public String toString(){
		return "当前的房价为 "+ this.price;
	}
	
	public static void main(String[] args) {
		Buyer b1 = new Buyer("购房者1");
		Buyer b2 = new Buyer("购房者2");
		Buyer b3 = new Buyer("购房者3");
		House house = new House();
		house.addObserver(b1);
		house.addObserver(b2);
		house.addObserver(b3);
		house.setPrice(199.123f);
	}
}


