package test.java.enumtest;

public class Enumtest {
	
	MyColor color ;
	
	public void test(){
	}
	
	public static void main(String[] args) {
		for (MyColor color : MyColor.values()) {
			System.out.println("toString() : "+ color.toString());
			System.out.println("valueOf : " +color.valueOf("RED"));
			System.out.println("description: " + color.getDescription());
			System.out.println("== " + (color == MyColor.BLUE));
		}
		System.out.println(MyColor.values());
	}

}
