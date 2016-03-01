package com.web.daoimpl;

import org.hibernate.SessionFactory;

/**
 * 公共方法，获取对象的总条数
 * @author lvbaolin 2015-06-18
 *
 */
public class ObjNumber {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 返回总条数
	 * @param hql hql语句
	 * @return
	 */
	public int getNumber(String hql) {
		// TODO Auto-generated method stub
		int obj_num=sessionFactory.getCurrentSession().createQuery(hql).list().size();
		return obj_num;
	}
}
