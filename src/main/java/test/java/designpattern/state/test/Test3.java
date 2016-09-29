package test.java.designpattern.state.test;

import junit.framework.TestCase;

public class Test3 {

}

class Creature {

}

interface State {
	String response();
}

class Frog implements State {
	@Override
	public String response() {
		return "Ribbet";
	}
}

class Prince implements State {
	private State s = new Frog();

	@Override
	public String response() {
		return "darling";
	}

	public void greet() {
		System.out.println(s.response());
	}

	public void kiss() {
		s = new Prince();
	}
}

class KissingPrincess extends TestCase {
	Creature c = new Creature();

	public void test() {
	}
}
