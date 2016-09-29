package test.java.designpattern.visitor;

public class FloatElement implements Visitable {
	public FloatElement(float num) {
		super();
		this.num = num;
	}

	private float num;

	public float getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFloat(this);
	}

}
