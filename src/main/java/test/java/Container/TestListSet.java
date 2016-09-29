package test.java.Container;
import java.util.*;

public class TestListSet {
	public static void main(String[] args) {
		//test1();
		//test2();
		ArrayList a = new ArrayList();
		long st=System.currentTimeMillis();
		for(int  i = 0 ; i<50000; i++){
			a.add(new Object());
		}
		long end =System.currentTimeMillis();
		System.out.println("Arr:   "+(end-st));
		long s1 = System.currentTimeMillis();
		for(int i =0;i<a.size();i++) {
			a.remove(i);
		}
		System.out.println(System.currentTimeMillis()-s1);
		
		
		
		LinkedList b = new LinkedList();
		long st2=System.currentTimeMillis();
		for(int  i = 0 ; i<50000; i++){
			b.addFirst(new Object());
		}
		long end2 =System.currentTimeMillis();
		System.out.println("Linked:  "+(end2-st2));
		long s2 = System.currentTimeMillis();
		for(int i = 0;i<b.size();i++) {
			b.remove(i);
		}
		System.out.println(System.currentTimeMillis()-s2);
	}
	
	
	
	public static void test2() {
		Integer[] s = new Integer[3];
		s[0]=345;
		s[1]=3;
		s[2]=347;
		Set<Integer> l = new HashSet<Integer>(Arrays.asList(s));
		System.out.println(max(l));
		
	}
	
	/**
	 * ���ԱȽ��κμ���,�������е����ֵ
	 * @param <E>
	 * @param c
	 * @return
	 */
	public static <E extends Comparable<E>> E max(Collection<E> c) {
		if(c.isEmpty() ) {
			throw new NoSuchElementException ();
		}
		Iterator<E> iter = c.iterator();
		E largest = iter.next();
		while(iter.hasNext()){
			E next = iter.next();
			if(largest.compareTo(next) < 0) {
				largest = next;
			}
		}
		return largest;
	}
	
	
	/**
	 * set�����ļ�,��map ��һ����ɾ��
	 * @param set
	 * @param map
	 */
	public static void test1(){
		Set<String> set = new HashSet<String>(5);
		Map<String,Object> map = new HashMap<String,Object>();
		set.add("a");
		set.add("b");
	//	set.add("c");
		map.put("a", "aaaaa");
		map.put("b","bbbbb");
		map.put("c", "ccccc");
		
		System.out.println(map);
		//map �ļ� �Ƿ�� ��set
		//System.out.println(map.keySet().retainAll(set));
		System.out.println(map.keySet());
		System.out.println(map);
		
		map.keySet().removeAll(set);
		
		System.out.println(map);
	}
}
