package test.java.test.java.designpattern.command;

public class MM {
	private String name ;
	
	
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	
	public void order(Boy b ){
		Command c = new ShoppingCommand();
		b.addCommand(c);
		c = new HugCommand();
		b.addCommand(c);
		b.doSomeThing();
	}
}
