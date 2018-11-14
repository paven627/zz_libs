package test.java.designpattern.chain.filter;

import java.util.ArrayList;
import java.util.List;

import test.java.designpattern.chain.filter.web.Request;
import test.java.designpattern.chain.filter.web.Response;

/**过滤链也实现了 Filter 接口 */
public class FilterChain implements Filter {
	List<Filter> filters = new ArrayList<Filter>();
	
	/**记录该调用哪一个filter 的计数器*/
	int index = 0 ;
	
	public FilterChain addFilter(Filter f ){
		this.filters.add(f);
		return this;
	}
	
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		if(index == filters.size()){
			return ;
		}
		//调用的时候就是调用的下一个
		Filter f = filters.get(index);
		index ++;
		f.doFilter(request, response, chain);
	}
}
