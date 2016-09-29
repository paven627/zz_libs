package test.java.designpattern.visitor;

import java.util.Collection;

//  
public interface Visitor {
	public void visitCollection(Collection<Visitable> collection);

	public void visitString(StringElement ele);

	public void visitFloat(FloatElement ele);
}
