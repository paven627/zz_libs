package test.java.test.java.designpattern.strategy;

/**
 * 排序,只要是实现了 Comparable 接口的对象都能传进sort方法
 * 算法的实现与具体的对象类型无关,耦合性低
 *
 * 但是比较的方法是已经写定的,如果一个类的对象有多种比较方法,
 * 就不能只有一个比较方法,需要定义比较器类
 */
public class DataSorter {
	public static void main(String[] args) {
		Cat[] a = {new Cat(5,5),new Cat(3,3),new Cat(1,1)};
		DataSorter.sort(a);
		p(a);
		
	}

	/**
	 * 只要实现了IComparable 接口,是什么对象就会
	 * 调用什么对象的CompareTo() 方法
	 */
	public static void sort(IComparable[] a){
		for(int i =0;i<a.length;i++){
			for(int j = 0; j < a.length -1 - i;j ++){
				IComparable<Object> o1 = (IComparable<Object>)a[j];
				IComparable<Object> o2 = (IComparable<Object>)a[j+1];
				if(o1.compareTo(o2) > 1 ){
					swap(a,j,j+1);
				}
				
			}
		}
	}
	private static void swap(Object[] a, int x, int y){
		Object temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static void swap(Object o1 ,Object o2){
		Object temp = o1;
		o1 = o2;
		o2 = temp;
	}
	
	public static void p(Object[] o){
		for (Object object : o) {
			System.out.println(object.toString());
		}
	}
}
