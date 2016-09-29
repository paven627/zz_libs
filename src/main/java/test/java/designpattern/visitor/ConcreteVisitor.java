package test.java.designpattern.visitor;

import java.util.Collection;
import java.util.Iterator;

public class ConcreteVisitor implements Visitor {

	@Override
	public void visitCollection(Collection<Visitable> collection) {
		Iterator<Visitable> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Visitable next = iterator.next();
			next.accept(this);
		}
	}

	@Override
	public void visitString(StringElement ele) {
		System.out.println(ele.getValue());
	}

	@Override
	public void visitFloat(FloatElement ele) {
		System.out.println(ele.getNum());
	}

}
