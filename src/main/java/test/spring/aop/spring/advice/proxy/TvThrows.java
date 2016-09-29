package test.spring.aop.spring.advice.proxy;

import org.springframework.aop.ThrowsAdvice;




public class TvThrows implements ThrowsAdvice {

	public void afterThrowing(Exception e) {
		System.out.println(e);
		System.out.println("通知仓库,赶紧订货");
	}

}
