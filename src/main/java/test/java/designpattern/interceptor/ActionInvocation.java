package test.java.designpattern.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 *  模拟 struts 拦截器,或者过滤器
 *
 */
public class ActionInvocation {
	List<IInterceptor> interceptors = new ArrayList<IInterceptor>();
	int index = -1;
	Action a = new Action();
	
	public ActionInvocation(){
		this.interceptors.add(new FirstInterceptor());
		this.interceptors.add(new SecondInterceptor());
	}
	
	public void invoke() {
		// 所有的过滤器都过滤完了,就去调用最后的执行方法
		index++;
		if(index >= this.interceptors.size()){
			a.execute();
			return;
		}
		this.interceptors.get(index).intercept(this);
	}

}
