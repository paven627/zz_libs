package test.java.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TestGroup {

	// (\\S*?)-(\\S*?)-(\\S*)
	// /\{[^\}]+\}/
	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		String input = "{aaaa.com},{bbbb.com},{cccc.com},";
		// \\{(\\S*?)\\}
		// Pattern p = Pattern.compile("\\{(.*?)\\},{0,4}");
		Pattern p = Pattern.compile("\\{([^\\}]*)\\}+");
		Matcher matcher = p.matcher(input);
		System.out.println(matcher.find());
		matcher.group(0);
		System.out.println(matcher.groupCount());
		for (int i = 0; i <= matcher.groupCount(); i++) {
			System.out.println(matcher.group(i));
		}
	}
	public static void test2() {
	}
}

// /\{(.*?)\}/gs