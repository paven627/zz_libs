package test.java.designpattern.state;

/**
 * 每个方法调用状态的方法,当前的状态确定了调用哪个状态的方法
 *
 */
public class MM {
	private String name ;
	private MMState state = new HappyState();
	
	public void smile(){
		state.smile();
	}
	
	public void cry(){
		state.cry();
	}
	
	public void say(){
		state.say();
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	
}
