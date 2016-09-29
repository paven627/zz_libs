package test.java.designpattern.visitor;

public class StringElement implements Visitable {
	private String value;

	public StringElement(String value) {
		super();
		this.value = value;
	}

	public StringElement() {
		super();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitString(this);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
