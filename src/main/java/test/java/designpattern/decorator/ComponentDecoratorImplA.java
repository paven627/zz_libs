package test.java.test.java.designpattern.decorator;

public class ComponentDecoratorImplA implements ComponentDecorator {

	private Component component;
	
	
	public ComponentDecoratorImplA(Component component) {
		super();
		this.component = component;
	}


	@Override
	public void show() {
		component.show();
		afterShow();
	}
	
	private void afterShow(){
		System.out.println("子类扩展方法");
	}
}
