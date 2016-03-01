package com.web.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.web.dao.UserD;
import com.web.entity.User;
import com.web.util.Page;

public class UserDAO implements UserD {

	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void getTest(){
		
	}
	
	@Override
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save((User)obj);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<?> getAll(Page page) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("from User");  
        //设置每页显示多少个，设置多大结果。  
        query.setMaxResults(page.getEveryPage());  
        //设置起点  
        query.setFirstResult(page.getBeginIndex());  
        return query.list();  
	}

	@Override
	public Object getIdObj(String id) {
		// TODO Auto-generated method stub
//		Object user=sessionFactory.getCurrentSession().get(User.class, id);
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public boolean updateObj(Object obj) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update((User)obj);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delObj(String id) {
		// TODO Auto-generated method stub
		try {
			User user=(User) getIdObj(id);
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object getIdObj(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delObj(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}



}
