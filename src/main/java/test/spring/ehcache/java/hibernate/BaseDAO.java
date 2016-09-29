package test.spring.ehcache.java.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * Spring ��ݷ��ʻ���,��HibernateDaoSupport����̳�
 * ÿһ��ʵ��������ݷ�����Ӵ˶���̳�
 * ʹ������ע�뽫ʵ������޶���ע�� poName ������
 * 
 */
public class BaseDAO extends HibernateDaoSupport{
	
	/**
	 * ʵ������ȫ·���޶���,����spring��ÿһ��ʵ�������޶���ע��
	 * ����,ÿһ�� DAO���󶼱���һ���Լ������Ķ�����޶���,���Է����ҵ����� 
	 * 
	 **/
	private String poName;
	
	/**
	 * ��ǰ�����ȫ�޶���
	 * @return
	 */
	public String getPoName() {
		return poName;
	}
	
	
	/**
	 * ��ʵ��������ӻ����޸�ͬ������ݿ�
	 * @param po
	 */
	public void saveOrUpdate(Serializable po){
		getHibernateTemplate().saveOrUpdate(po);
	}
	
	/**
	 * ɾ��һ������
	 * @param entity ��Ҫɾ���ʵ�����
	 */
	public void delete(Serializable entity){
		getHibernateTemplate().delete(entity);
	}
	
	/**
	 * ���ʵ�����Id,�Ȳ��Ҵ�ʵ��,Ȼ��ɾ��
	 * @param id
	 */
	public void delete(Integer id){
		Serializable s = this.findById(id);
		this.delete(s);
	}
	
	
	/**
	 * ���һ��Id ����һ��ʵ�����		
	 * @param id
	 * @return
	 */
	public Serializable findById(Integer id){
		if(id == null){
			return null;
		}
		Serializable obj = null;
		try{
			obj = (Serializable)getHibernateTemplate().get(poName, id); 
		} catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	
	
	/**
	 * ��ȡ���д�ʵ�����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Serializable> getObjects(){
		List<Serializable> list = null;
		String hql = "from " + poName ;
		list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	
	/**
	 * ��ݶ������������ѯ����
	 * @param hql HQL ��ѯ���
	 * @param params ��ѯʱ����������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Serializable> searchByHQL(String hql,Object...params){
		Query query = this.getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	

	public void setPoName(String poName) {
		this.poName = poName;
	}
	
	
}
