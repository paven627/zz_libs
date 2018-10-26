package test.java.test.java.designpattern.chain.filter;

import test.java.test.java.designpattern.chain.filter.web.Request;
import test.java.test.java.designpattern.chain.filter.web.Response;

public class SensitiveFilter implements Filter{
	
	@Override
	public void doFilter(Request request, Response response ,FilterChain chain) {
		request.setRequestString(request.getRequestString().replace("敏感","")+"--sessitive-request--");
		
		//调用下一个过滤
		chain.doFilter(request, response, chain);
		
		response.setResponseStr(response.getResponseStr()+"--sess-response--");
	}
	
}
