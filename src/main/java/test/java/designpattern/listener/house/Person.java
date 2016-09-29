package test.java.designpattern.listener.house;


public class Person implements PriceChangeListener{

	private String name ;
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void priceChanged(PriceChangeEvent pce) {
		System.out.println(name + "  检测到价格变动了: 新价格" + pce.getPrice());
		
	}


	
	
}


