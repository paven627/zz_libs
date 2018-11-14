package test.java.designpattern.strategy.sxt.strategy;

import test.java.designpattern.strategy.IComparator;

public class CatWeightComparator implements IComparator<Cat> {

	@Override
	public int compare(Cat o1, Cat o2) {
		Cat c1 = (Cat)o1;
		Cat c2 = (Cat)o2;
		if(c1.getWeight() > c2.getWeight()) return -1;
		else if(c1.getHeight() < c2.getHeight()) return 1;
		return 0;
	}

}
