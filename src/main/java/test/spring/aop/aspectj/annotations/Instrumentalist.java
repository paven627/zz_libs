package test.spring.aop.aspectj.annotations;

import org.springframework.stereotype.Component;

@Component("actor")
public class Instrumentalist implements Performer {

	@Override
	public String toString() {
		return "Instrumentalist演员类";
	}

	@Override
	public void perform(int i) throws Exception {
		if (i == 1)
			throw new Exception("演出失败");
		System.out.println("演员正在演出");
	}

	@Override
	public void perform() throws Exception {
		System.out.println("不带参数方法");
	}

}
