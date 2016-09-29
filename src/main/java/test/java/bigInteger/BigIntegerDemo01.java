package test.java.bigInteger;

import java.math.BigInteger ;
public class BigIntegerDemo01{
	public static void main(String args[]){
		BigInteger bi1 = new BigInteger("123456789") ;	// ����BigInteger����
		BigInteger bi2 = new BigInteger("987654321") ;	// ����BigInteger����
		System.out.println("�ӷ�������" + bi2.add(bi1)) ;	// �ӷ�����
		System.out.println("����������" + bi2.subtract(bi1)) ;	// ��������
		System.out.println("�˷�������" + bi2.multiply(bi1)) ;	// �˷�����
		System.out.println("�������" + bi2.divide(bi1)) ;	// �����
		System.out.println("�����" + bi2.max(bi1)) ;	 // ��������
		System.out.println("��С��" + bi2.min(bi1)) ;	 // �����С��
		BigInteger result[] = bi2.divideAndRemainder(bi1) ;	// �������ĳ����
		System.out.println("���ǣ�" + result[0] + 
			"�������ǣ�" + result[1]) ;
	}
}
