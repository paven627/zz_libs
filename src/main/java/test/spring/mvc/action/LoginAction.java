package test.spring.mvc.action;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

import test.spring.mvc.bean.LoginBean;


public class LoginAction extends SimpleFormController  {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String viewPage;

	protected ModelAndView onSubmit(Object command) throws Exception {
		// 在onSummit()方法中处理用户请求
		LoginBean loginbean = (LoginBean) command;
		Map model = new HashMap();
		model.put("userName", loginbean.getUserName());
		// 将loginbean中的userName存入model
		ModelAndView mv = new ModelAndView(getSuccessView(), model);
		
		return mv;
	}

	
	
	public void setViewPage(String viewPage) {
		// 调用该方法给属性viewPage赋值
		this.viewPage = viewPage;
	}

	public String getViewPage() { // 调用该方法获取viewPage属性
		return this.viewPage;
	}







}
