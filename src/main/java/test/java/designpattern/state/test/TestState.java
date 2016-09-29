package test.java.designpattern.state.test;

public class TestState {
	public static void main(String[] args) {
		ComputerState state = new BadState();
		Computer s = new Computer();
		s.setState(state);
		s.start();
		s.close();
	}
}
interface ComputerState {
	void start();
	void close();
}
class GoodState implements ComputerState{
	@Override
	public void close() {
		System.out.println("好状态下,关闭10秒");
	}
	@Override
	public void start() {
		System.out.println("好状态下,启动20秒");
	}
}
class BadState implements ComputerState{
	@Override
	public void close() {
		System.out.println("坏状态下,关闭30秒");
	}
	@Override
	public void start() {
		System.out.println("坏状态下,启动50秒");
	}
}

class Computer {
	ComputerState state;
	public void close() {
		state.close();
	}
	public void start() {
		state.start();
	}
	public void setState(ComputerState s){
		this.state=s;
	}
	public ComputerState getState() {
		return state;
	}
}
