package test.java.designpattern.listener.house;

import java.util.ArrayList;
import java.util.List;

public class House {
	
	private List<PriceChangeListener> peple = new ArrayList<PriceChangeListener>();

	private double price;
	
	public List<PriceChangeListener> getPeple() {
		return peple;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		for (PriceChangeListener pp : peple) {
			pp.priceChanged(new PriceChangeEvent(this.price));
		}
	}

	public void addEventListener(PriceChangeListener p){
		this.peple.add(p);
	}
	
	
	
	public static void main(String[] args) {
		Person p = new Person("p1");
		Person p2 = new Person("p2");
		House h = new House();
		h.addEventListener(p);
		h.addEventListener(p2);
		
		h.setPrice(100);
		
		
	
		
	}
	
}
