package test.java.concurrent.mydeadlock;

/**
 * ���߳�t.start() ֮��,���û��Thread.sleep()����, ���̺߳����̵߳�ִ��˳�򽫻᲻ȷ�� ������ִ�����߳�
 * m1����,Ҳ������ִ�����߳� m2����, ���������˳�������� ���߳�sleep ֮�󽫻���ִ�����߳�,ִ�е�˳��ᱻȷ��
 * 
 * ���������������벻����ִ�еĽ��Ҳ���в�ͬ
 */
public class Test3 implements Runnable {
	int b = 100;

	public static void main(String[] args) throws Exception {
		Test3 tt = new Test3();
		Thread t = new Thread(tt);
		t.start();
		// Thread.sleep(100);
		tt.m2();
		System.out.println("Test.b==" + tt.b);
	}

	public void run() {
		try {
			m1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void m1() throws Exception {
		b = 1000;
		Thread.sleep(500);
		System.out.println("m1()  b==" + b);
	}

	public synchronized void m2() throws Exception {
		Thread.sleep(250);
		b = 2000;
		System.out.println("m2 , b==" + b);
	}
}
