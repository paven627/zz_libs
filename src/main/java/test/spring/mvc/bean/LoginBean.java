package test.spring.mvc.bean;

public class LoginBean {
	
	 private String userName;            //该属性用于获取表单中的userName参数  
	    private String password;            //用于获取表单中的password参数  
	    
	    
	    
	    public String getUserName(){  
	        return userName;  
	    }  
	    public void setUserName(String userName){  
	        this.userName = userName;  
	    }  
	    public String getPassword(){  
	        return this.password;  
	    }  
	    public void setPassword(String password){  
	        this.password = password;  
	    }  
}
