package test.java.fibonacci;

/**
 * �ݹ�,�Լ� fabonacci ���е� 1,1,2,3,5,8,13
 */

//
public class MyFibonacci {
	public static void main(String[] args) {
		System.out.println(fibo(5)); // �ݹ�
		System.out.println(fibo2(5)); // for ѭ��
		System.out.println(Integer.MAX_VALUE);
	}

	public static int fibo(int index) {
		if (index == 1 || index == 2) {

			return 1;
		} else {

			return fibo(index - 1) + fibo(index - 2);
		}
	}

	// ��������λ��,���ظ�λ�õ�����ֵ
	public static int fibo2(int index) {
		int a = 1;
		int b = 1;
		int c = 0;
		if (index == 1 || index == 2) {
			return 1;
		}
		for (int i = 3; i <= index; i++) {
			System.out.printf("%d,", i);
			c = a + b;
			a = b;
			b = c;

		}
		return c;
	}

}