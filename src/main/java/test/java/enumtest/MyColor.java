package test.java.enumtest;

public enum MyColor {
//	RED ("hong",1)
//	,BLUE("蓝色",2)
//	,YELLOW("黄色",3);
	
	// 声明
	RED ("红色"){
		public String toString(){
			return "红色";
		}
		
	}
	,BLUE("蓝色")
	,YELLOW("黄色");

	private String description;
	
// 可以自定义内部构造器
	private MyColor(String desc){
		this.description = desc;
	}

	
	@Override
	public String toString(){
		String id = name();
		System.out.println("name : " + name());
		String lowwer = id.substring(1).toLowerCase();
		return id.charAt(0) + lowwer;
	}
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
