package test.java.designpattern.bridge;

public class Boy {
	private String name ;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void pursue(MM mm){
		Gift g = new WarmGift(new Flower());
		Gift r = new WildGift(new Ring());
	}
	
	public void give(Gift gift, MM mm){
		
	}
}
