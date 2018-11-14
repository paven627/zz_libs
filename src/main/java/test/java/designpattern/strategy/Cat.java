package test.java.designpattern.strategy;

public class Cat implements IComparable<Cat>{
	private int height;
	private int weight;
	
	// 后一种方法 添加的比较器, 想更换比较结果方法是,换比较器就可以了
	private IComparator<Cat> comparator = new CatHeightComparator();
	
	public IComparator<Cat> getComparator() {
		return comparator;
	}

	public void setComparator(IComparator<Cat> comparator) {
		this.comparator = comparator;
	}

	@Override
	public String toString(){
		return String.format("高:%s,重:%s", this.height,this.weight);
	}
	
	public Cat(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Cat t) {
		return comparator.compare(this, t);
	}


//	@Override
//	public int compareTo(Cat cat) {
//		if(cat instanceof Cat){
//			int height = cat.getHeight();
//			if(this.getHeight()> height){
//				return 1;
//			}else if(this.height<height){
//				return -1; 
//			}
//			return 0;
//		}
//		throw new IllegalArgumentException();
//	}


}
