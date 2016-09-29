package test.java.concurrent.thread;

public class SecondThread {
	public static void main(String[] args) {
		MyThread2 t = new MyThread2();
		//Thread th =new Thread(t);
		//th.run();
		
//		Thread t1 = new MyThread2();
//		Thread t2 = new MyThread2();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
	
//		MyThread3 t3 = new MyThread3();
//		Thread t = new Thread(t3);
//		t.start();
			
	}
	
	
}

class MyThread2 extends Thread {
	 int count = 0;
	@Override
	public void run()
	{
		for (int i = 0; i < 500; i++) {
			System.out.println("count == "+count +" ,  i =" + i+"name = " +Thread.currentThread().getName());
			count ++;
		}
	
	}
	
}

class MyThread3 extends Thread {
	int i = 0;
	public void run() {
		while (true) {
			System.out.println("i =" + i+"name = " +Thread.currentThread().getName());
			if(i >= 100) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
			
	}
	
	
}





