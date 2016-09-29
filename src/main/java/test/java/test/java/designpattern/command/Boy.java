package test.java.test.java.designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Command 模式, 命令模式
 *
 */
public class Boy {
	private String name ;
	
	/**执行的命令*/
	List<Command> commands = new ArrayList<Command>();
	
	public void doSomeThing() {
		for (Command c : commands) {
			c.execute();
		}
	}
	public void addCommand(Command c){
		this.commands.add(c);
	}
	
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void undoCommand(){
		
	}
	
}
