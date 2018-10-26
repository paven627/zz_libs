package test.java.test.java.designpattern.chain.filter;

import test.java.test.java.designpattern.chain.filter.web.Request;
import test.java.test.java.designpattern.chain.filter.web.Response;

public class HtmlFilter implements Filter{

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		request.setRequestString(request.getRequestString(
				).replace('<', '[').replace('>', ']')+" --html-request--");
	
		chain.doFilter(request,response,chain);
		response.setResponseStr(response.getResponseStr() 
				+ "---HTML-response---");
	}

	
}
