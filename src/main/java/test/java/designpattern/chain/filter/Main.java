package test.java.designpattern.chain.filter;

import test.java.designpattern.chain.filter.web.Request;
import test.java.designpattern.chain.filter.web.Response;


/**
 * 过滤链本身也实现了Filter接口,本身也可以看做是一个大的Filter
 *
 */
public class Main {
	public static void main(String[] args) {
		String msg = "大家好:),<script>,敏感信息";
		Request request = new Request();
		request.setRequestString(msg);
		Response response = new Response();
		response.setResponseStr("回应");
		
		FilterChain fc = new FilterChain();
		fc.addFilter(new HtmlFilter())
				.addFilter(new SensitiveFilter());
		fc.doFilter(request, response ,fc);
		
		System.out.println(request.getRequestString());
		System.out.println(response.getResponseStr());
		
		
	}
}
