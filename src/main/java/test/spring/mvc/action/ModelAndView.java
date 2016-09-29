package test.spring.mvc.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.View;




public class ModelAndView {
	private Object view;
	// 该属性用来存储返回的视图信息
	private Map model;

	// 该属 性用来存储处理后的结果数据
	// 下面是一些参数不同的构造函数，用来创建ModelAndView实例
	public ModelAndView() {
	}

	public ModelAndView(View view) {
		this.view = view;
	}

	public ModelAndView(String viewName) {
		this.view = viewName;
	}

	public ModelAndView(View view, Map model) {
		this.view = view;
		this.model = model;
	}

	public ModelAndView(String viewName, Map model) {
		this.view = viewName;
		this.model = model;
	}

	public ModelAndView(View view, String modelName, Object modelObject) {
		this.view = view;
		addObject(modelName, modelObject);
	}

	public ModelAndView(String viewName, String modelName, Object modelObject) {
		this.view = viewName;
		addObject(modelName, modelObject);
	}

	// view属性的getter、setter方法，可以看出view属性可以是一个View类的实例，也可以是一个String
	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return (this.view instanceof View ? (View) this.view : null);
	}

	public void setViewName(String viewName) {
		this.view = viewName;
	}

	public String getViewName() {
		return (this.view instanceof String ? (String) this.view : null);
	}

	public boolean isReference() {
		return (this.view instanceof String);
	}

	// 下面的方法用来给model属性赋值、或者获取该属性的值，model是一个Map类型的属性
	protected Map getModelInternal() {
		return this.model;
	}

	public Map getModel() {
		if (this.model == null) {
			this.model = new HashMap(1);
		}
		return this.model;
	}

	public ModelAndView addObject(String modelName, Object modelObject) {
		getModel().put(modelName, modelObject);
		return this;
	}

	public ModelAndView addAllObjects(Map modelMap) {
		getModel().putAll(modelMap);
		return this;
	}
}