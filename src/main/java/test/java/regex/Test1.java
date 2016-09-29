package test.java.regex;
import java.util.regex.*;
public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String regex = "\\d[a-z]{3}[0-9]";
		String r2 = "\\w+[-+.]w+@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*$";

		Pattern p2 = Pattern.compile(r2);
		System.out.println(p2.toString());
		Matcher m2 =p2.matcher("aewr234@wrer.erar.aer.aer");
		System.out.println(m2.matches());

		Pattern p = Pattern.compile(regex);
		String s ="abc3";
		Matcher  m = p.matcher(s);
		System.out.println(m.matches());
	
		
		
		System.out.println("*****************");
		System.out.println("abc3".matches(regex));
		
	}

}
