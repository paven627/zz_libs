package test.java.designpattern.chain.filter;

import test.java.designpattern.chain.filter.web.Request;
import test.java.designpattern.chain.filter.web.Response;

interface Filter  {
	void doFilter(Request request, Response  response,FilterChain chain);
}
