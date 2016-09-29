package test.java.beanutils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;


/**
 * BeanUtils 转换属性时使用String ,封装了类型转换,
 * PropertyUtils 操作属性时需要按照属性的数据类型操作
 * 
 * BeanUtils 转换Map 和 对象时,遇到不能转换的类型,可以注册转换器
 * 
 */
public class TestDescriptor {
	Person person = new Person();

	public static void main(String[] args) throws Exception {
		TestDescriptor test = new TestDescriptor();
		test.person.setName("小明");
		test.person.setAge(15);
		System.out.println("----------  PropertyDescriptor -----------");
		test.testPropertyDescriptor();
		System.out.println("----------  BeanInfo -----------");
		test.testBeanInfo();
		System.out.println("----------  property -----------");
		test.testSetProperty();

		System.out.println("---------  Map 和 bean ----------");
		test.testMap();
		test.testMapToBean();		
		System.out.println("---------- propertys Util -----------");
		test.testPropertyUtils();
	}
	
	// PropertyUtils ,需要按照数据类型使用
	private void testPropertyUtils () throws Exception{
		PropertyUtils.setProperty(person, "age", 23);
		System.out.println(person.getAge());
		
		Map desc = PropertyUtils.describe(person);
		System.out.println(desc);
	}

	/**
	 * map 和 对象的转换可以注册转换器
	 */
	private void testMapToBean() throws IllegalAccessException, InvocationTargetException{
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		map.put("birthday", dateStr);
		map.put("name", "小红");
		map.put("age", 28);
		
		Dept dept1  = new Dept();
		dept1.setDeptName("技术部");
		dept1.setPersonNum(20);
		map.put("dept", dept1);
		DateTimeConverter dtConverter = new DateConverter();
		dtConverter.setPattern("yyyy-MM-dd");
		ConvertUtils.register(dtConverter, Date.class);
		Converter lookup = ConvertUtils.lookup(Date.class);
		System.out.println(lookup);
		Person p2 = new Person();
		BeanUtils.populate(p2, map);
		System.out.println("BeanUtils.populate: "+ p2);
		
		
	}
	// 对象转 map 
	private void testMap() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InstantiationException {
		Object cloneBean = BeanUtils.cloneBean(person);
		System.out.println("BeanUtils.CloneBean : "+cloneBean);
		person.setBirthday(new Date());
		Map map = BeanUtils.describe(person);
		System.out.println("BeanUtils.describe : "+ map);
		
 	}

	
	// 使用BeanUtils 的 getProperty  setProperty ,  copyProperties
	public void testSetProperty() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		person.setName("新名字");
		person.setAge(12);
		String value = BeanUtils.getProperty(person, "name");
		System.out.println("value: " + value);
		BeanUtils.setProperty(person, "name", "小明");
		System.out.println("更改名字之后: " + person.getName());
		BeanUtils.setProperty(person, "birthday.time", System.nanoTime());
		System.out.println(BeanUtils.getProperty(person, "birthday.time"));
		Dept dept1 = new Dept();
		dept1.setDeptName("销售部");
		dept1.setPersonNum(10);
		BeanUtils.setProperty(person, "dept", dept1);
		System.out
				.println("BeanUtils中birthday.time 相当于 getBirthday().getTime() 方法");

		// 复制对象
		Person p2 = new Person();
		BeanUtils.copyProperties(p2, person);
		System.out.println("新对象复制后: " + p2);
	}

	//  jdk 中的 内省 方法 propertyDescriptor,  BeanInfo
	public void testBeanInfo() throws IntrospectionException {
		person.setName("aa");
		BeanInfo beanInfo = Introspector.getBeanInfo(person.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getName().equals("name")) {
				Object value = propertyDescriptor.getValue("name");
				System.out.println("value:" + value);
			}
		}
	}

	// jdk PropertyDescriptor
	private void testPropertyDescriptor() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			IntrospectionException {
		PropertyDescriptor desc = new PropertyDescriptor("name", Person.class);
		Method writeMethod = desc.getWriteMethod();
		writeMethod.invoke(person, "名字");
		Method readMethod = desc.getReadMethod();
		Object retVal = readMethod.invoke(person);
		System.out.println("name :" + retVal);
	}
}
