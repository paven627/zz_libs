public class MyTest {
	public static void main(String[] args) {
		char s = ' ' ;
		
		int x = ' ';
		
		boolean defined = Character.isDefined(s);
		System.out.println(defined);
		boolean identifierIgnorable = Character.isIdentifierIgnorable(s);
		System.out.println(identifierIgnorable);
		boolean letterOrDigit = Character.isLetterOrDigit(s);
		System.out.println(letterOrDigit);
		boolean spaceChar = Character.isSpaceChar(s);
		System.out.println(spaceChar);
		boolean whitespace = Character.isWhitespace(s);
		System.out.println(whitespace);
		Character ch = s;
		System.out.println(ch);
		
	}
	
	
	private static void code(char c){
		char code;  
	    code = (char)(c + 1554 % 10);  
	    System.out.println('4'-48);  
	    System.out.println(code);  
	    System.out.println((int)code);  
	    code = 4;  
	    System.out.println(0+Character.valueOf(code));  
	      
	      
	    System.out.println((char)(0));  
	    //强制转换，（取到低16位）4个字节的取低2个字节  
	    char char1=(char)4;  
	    System.out.println((char)(c+char1));  
	      
	      
	      
	    System.out.println(0+Character.valueOf((char)0));  
	}
	
}