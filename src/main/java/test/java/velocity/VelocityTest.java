package test.java.velocity;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;


public class VelocityTest {

	@Test
	public void helloWorld(){
		Velocity.init("src/velocity.properties");
		
		//创建上下文环境,可以存放模板用到的数据对象
		VelocityContext context = new VelocityContext(); 
		context.put("hello", "你好");
		
		//获取模板
		Template tm = Velocity.getTemplate("helloWorld.vm");
		
		StringWriter writer = new StringWriter();
		
		tm.merge(context, writer);
		
		System.out.println(writer.toString());
	}
	
	
	
	@Test
	public void test2(){
		Velocity.init("src/velocity.properties");
		
		//创建上下文环境,可以存放模板用到的数据对象
		VelocityContext context = new VelocityContext(); 
		context.put("person", new Person(1,"一个人"));
		
		//获取模板
		Template tm = Velocity.getTemplate("person.vm");
		//创建字符流 
		StringWriter writer = new StringWriter();
		//将模板和流合并,用于输出
		tm.merge(context, writer);
		
		System.out.println(writer.toString());
	}
	
	
	
	@Test
	public void test3(){
		Velocity.init("src/velocity.properties");
		
		//创建上下文环境,可以存放模板用到的数据对象
		VelocityContext context = new VelocityContext(); 
		context.put("persons", 
				new Person[]{new Person(1,"一个人"),new Person(2,"老张")});
		
		//获取模板
		Template tm = Velocity.getTemplate("persons.vm");
		
		StringWriter writer = new StringWriter();
		
		tm.merge(context, writer);
		
		
		System.out.println(writer.toString());
	}
	
	
	@Test
	public void test4(){
		Velocity.init("src/velocity.properties");
		
		Map<String,Person> maps = new HashMap<String,Person>();
		maps.put("第一个",new Person(1,"老张"));
		maps.put("第二个",new Person(2,"老李"));
		for (Map.Entry<String, Person> entry : maps.entrySet()) {
			System.out.println(entry.getKey() + " : "+ entry.getValue());
		}
		
		//创建上下文环境,可以存放模板用到的数据对象
		VelocityContext context = new VelocityContext(); 
		context.put("maps",maps);
		
		//获取模板
		Template tm = Velocity.getTemplate("maps.vm");
		StringWriter writer = new StringWriter();
		tm.merge(context, writer);
		System.out.println(writer.toString());
	}
	
	
	@Test
	public void test5(){
		Velocity.init("src/velocity.properties");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//创建上下文环境,可以存放模板用到的数据对象
		VelocityContext context = new VelocityContext(); 
		Date date = new Date();
		context.put("sdf", sdf);
		context.put("date", date);
		
		//获取模板
		Template tm = Velocity.getTemplate("date.vm");
		
		StringWriter writer = new StringWriter();
		tm.merge(context, writer);
		System.out.println(writer.toString());
	}
	
	
}
