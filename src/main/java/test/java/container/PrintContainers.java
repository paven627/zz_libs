package test.java.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

//* java �еĸ�������,�洢˳������ͬ*/
public class PrintContainers {
	static Collection<String> fill(Collection<String> collection) {
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}
	
	static Map<String,String> fill(Map<String,String> map) {
		map.put("rat", "Fuzzy");
		map.put("cat", "rags");
		map.put("dog", "Bosco");
		map.put("dog", "Spot");
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println("���˳�򱣴�"+fill(new ArrayList<String>()));	//��������˳�򱣴�
		System.out.println("����˳�򱣴�"+fill(new LinkedList<String>()));	// ����˳�򱣴�
		System.out.println("hash"+fill(new HashSet<String>()));	//���Ļ�ȡ��ʽ,  ��ĸ˳�����, ���Ҳ�����ظ���,�൱���ӵķ�ʽ����Ԫ��,�� thinking in java ��17��
		System.out.println("�ȽϽ�����򱣴�"+fill(new TreeSet<String>()));	//�ȽϽ�����򱣴�
		System.out.println("���˳�򱣴�"+fill(new LinkedHashSet<String>()));	//���˳�򱣴� ,������ظ���
		System.out.println("���Ĳ��Ҽ���"+fill(new HashMap<String,String>()));	//  ���Ĳ��Ҽ���,û�а�����˳�򱣴�, ������ظ� ,
		System.out.println("��ĸ���򱣴�"+fill(new TreeMap<String,String>()));	//��ĸ���򱣴�,������ظ�
		System.out.println("���˳�򱣴�"+fill(new LinkedHashMap<String,String>()));	//���˳�򱣴�,������ظ�,�� HashMap һ��Ĳ�ѯ�ٶ�
		
		
		
		
	}

}
