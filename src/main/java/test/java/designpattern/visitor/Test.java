package test.java.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		Visitor visitor = new ConcreteVisitor();
		StringElement se = new StringElement("abc");
		se.accept(visitor);

		FloatElement fe = new FloatElement(new Float(1.5));
		fe.accept(visitor);
		System.out.println("===========");
		List<Visitable> result = new ArrayList<Visitable>();
		result.add(new StringElement("abc"));
		result.add(new StringElement("abc"));
		result.add(new StringElement("abc"));
		result.add(new FloatElement(new Float(1.5)));
		result.add(new FloatElement(new Float(1.5)));
		result.add(new FloatElement(new Float(1.5)));
		visitor.visitCollection(result);
	}

}
