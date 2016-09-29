package test.java.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSpider {
	public static void main(String[] args) {
		File file = new File("F:/��ѧ����ʿ��Java/html.txt");
		Pattern p =Pattern.compile("[\\w[.-]]+@[A-Za-z0-9]+(.|-)[a-zA-Z0-9]+.\\w+");
		Matcher m = null;
		 try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s  = null;
			while((s = br.readLine()) != null){
				m = p.matcher(s);				
				while(m.find()){
					System.out.println(m.group());
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

}
