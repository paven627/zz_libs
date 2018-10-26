package test.java.test.java.designpattern.strategy;

public class CatHeightComparator implements IComparator<Cat> {

	@Override
	public int compare(Cat o1, Cat o2) {
		Cat c1 = o1;
		Cat c2 = o2;
		return c1.getHeight() - c2.getHeight();
	}

}
