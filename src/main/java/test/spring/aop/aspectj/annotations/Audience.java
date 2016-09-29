package test.spring.aop.aspectj.annotations;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AspectJ 注解方式实现AOP
 * 
 * @Aspect 声明一个切面
 */
@Component("audi")
@Aspect
public class Audience {
	// 声明一个切入点
	@Pointcut("execution (* aop.aspectj.annotations..*.*(..))")
	public void performance() {
	}

	public void perform() {
		System.out.println("开始演出");
	}

	@Before("performance()")
	public void takeSeats() {
		System.out.println("观众就坐");
	}

	@AfterReturning("performance()")
	public void turnOffCellPhones() {
		System.out.println("演出完成");
	}

	public Audience() {
		super();
	}

	@Around("performance()")
	public void watchPerform(ProceedingJoinPoint pop) {
		System.out.println("------------------");
		printInfo(pop);
		System.out.println("------------------");
		try {
			pop.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void printInfo(ProceedingJoinPoint pop) {
		// 被代理对象
		Object target = pop.getTarget();
		System.out.println(target);
		Signature sig = pop.getSignature();// .toLongString() 取得完整的类名带参数

		String methodName = sig.getName();
		try {

			Class<?> cls = target.getClass();
			Method method = cls.getMethod(methodName);
			System.out.println(method);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String name = methodName;
		System.out.println(name);

		pop.getArgs(); // 取得传入的参数

		System.out.println("getTarget: " + target); // 得到当前被代理的类名

	}

	@AfterThrowing("performance()")
	public void demandRefund() {
		System.out.println("演出失败,观众要求退款");
	}
}
