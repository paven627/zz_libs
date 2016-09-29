package test.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercies {
	public static void main(String[] args) {
		Pattern p =Pattern.compile("\\d{3,5}");
		String s ="123-4567-15647";
		Matcher m =p.matcher(s);
		//matches() ���ƥ�� 
		//System.out.println(m.matches()); 
		testEmail(m);
		testFind(m);
		testLookingAt(m);
		testCase();
	}
	/** ����Դ�ַ��Сд,һ�����,�ó���д���ͳ���ֵд��������
	 * ���ҽ���Сд��ͬ���ַ��滻
	 */
	private static void testCase() {
		System.out.println("***************************");
		//Pattern p = Pattern.compile("java",2);
		Pattern p = Pattern.compile("java",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("aewrearjavaaewraer Java JAVa JAvA jaVaaeraeraer");
		while(m.find()){
			System.out.print(m.group()+" ");
		}
		System.out.println("\n*********replaceAll***********");
		System.out.print(m.replaceAll("JAVA")+" ");
		m.reset();
		System.out.println("\n**********�����滻Ϊ��д,˫���滻ΪСд***********");
		/** �����滻Ϊ��д,˫���滻ΪСд */
		int i = 0;
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			i++;
			System.out.println(m.group(0));
			if(i%2 == 1){
				m.appendReplacement(sb , "JAVA");
			}else{
				m.appendReplacement(sb, "java");
			}
		}
		System.out.println(sb);		
		m.appendTail(sb);
		System.out.println(sb);		
	}
	/** ���׵�Email ��֤*/
	private static void testEmail(Matcher m) {
		System.out.println("***************************");
		String regex ="[\\w[.-]]+@[A-Za-z0-9]+(.|-)[a-zA-Z0-9]+.\\w+";
		System.out.println("warasfsfs@arsaf.com".matches(regex));
	}
	private static void testFind(Matcher m) {
		System.out.println("***************************");
		while(m.find()){
			//�ҵ�֮��,�����ʼλ�úͽ���Ϊֹ,Ȼ��ƥ���ַ��ӡ
			System.out.print(m.start()+"-"+m.end()+",");
			System.out.println(m.group());
		}
	}
	private static void testLookingAt(Matcher m) {
		System.out.println("***************************");
		// lookingAt() ÿ�ζ��ӿ�ʼλ�ò���
		System.out.println(m.lookingAt()+" ,"+m.start());
		System.out.println(m.lookingAt()+" ,"+m.start());
	}

}
