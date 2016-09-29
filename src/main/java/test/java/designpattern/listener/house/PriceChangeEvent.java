package test.java.designpattern.listener.house;

public class PriceChangeEvent {
	private double price;

	public double getPrice() {
		return price;
	}

	public PriceChangeEvent(double price) {
		super();
		this.price = price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
