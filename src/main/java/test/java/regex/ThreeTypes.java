package test.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ʽ����
 * Greedy quantifiers ̰����
 * Reluctant  quantifiers ��ǿ��
 * Possessive quantifiers  ��ռ��
 */
public class ThreeTypes {
	public static void main(String[] args) {
		greedy();
		reluctant();
		possessive();
	}
	
	/**
	 * possessive ռ�е� 
	 * .{3,10}+[0-9] һ�ζ�ȡ����ƥ��λ�� 10λ, �������û��ƥ���[0-9]��
	 *  ����Ҳ���������һλ,���Խ� "ռ�е�"
	 */
	private static void possessive() {
		String test = "aaaa5bbbb0";
		String regStr = ".{3,10}+[0-9]";
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(test);
		if(m.find()){
			System.out.println("possessive ƥ���λ�� :  " + m.start()+"-"+m.end());
		}else
			System.out.println("possessive û��ƥ��");
			
	}

	/**
	 * reluctant ��ǿ�ı�����  
	 * .{3,10}?[0-9] ���ȶ���3λ�ַ�,���ֲ�ƥ�� 
	 *  ���Ŷ���,����5λ����ƥ�䷵�ؽ��,�����ƥ�����,��˽� "��ǿ��"
	 */
	private static void reluctant() {
		String test = "aaaa5bbbb0";
		String regStr = ".{3,10}?[0-9]";
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(test);
		if(m.find()){
			System.out.println("reluctant ƥ���λ�� :  " + m.start()+"-"+m.end());
		}
	}

	/**
	 * ̰��ʽ , .{3,10} ����һ�ζ���10���ַ�,���ֲ�ƥ���
	 * �����һ���ַ�,����ƥ��
	 * һ��ȫ����ȡ.��˽�"̰����"
	 */
	private static void greedy() {
		String test = "aaaa5bbbb0";
		String regStr = ".{3,10}[0-9]";
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(test);
		if(m.find()){
			System.out.println("greedy ƥ���λ��:  " + m.start()+"-" +m.end());
		}
	}

}
