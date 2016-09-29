package test.spring.aop.aspectj.xml.interceptor;

import org.aspectj.lang.JoinPoint;

import test.spring.aop.aspectj.xml.model.Employee;

public class Camera {

	public void before(JoinPoint joinPoint) {
		Employee emp = (Employee) joinPoint.getTarget();
		System.out.println("����ͷ����  " + emp.getEmpName() + " ���� "
				+ joinPoint.getSignature().getName());
	}

}
