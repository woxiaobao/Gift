package com.web.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.web.dao.RoleD;
import com.web.entity.Role;
import com.web.util.Page;

public class RoleDAO implements RoleD {

	
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
			sessionFactory.getCurrentSession().save((Role)obj);
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
		Query query = sessionFactory.getCurrentSession().createQuery("from Role");  
        //设置每页显示多少个，设置多大结果。  
        query.setMaxResults(page.getEveryPage());  
        //设置起点  
        query.setFirstResult(page.getBeginIndex());  
        return query.list();  
	}

	@Override
	public Object getIdObj(String id) {
		// TODO Auto-generated method stub
//		Object role=sessionFactory.getCurrentSession().get(Role.class, id);
		return sessionFactory.getCurrentSession().get(Role.class, id);
	}

	@Override
	public boolean updateObj(Object obj) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update((Role)obj);
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
			Role role=(Role) getIdObj(id);
			sessionFactory.getCurrentSession().delete(role);
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
		return sessionFactory.getCurrentSession().createQuery("from Role").list();
	}

	@Override
	public void delSetModule(String id) {
		// TODO Auto-generated method stub
		Role role=(Role) sessionFactory.getCurrentSession().get(Role.class, id);
		role.getSetModule().removeAll(role.getSetModule());
		sessionFactory.getCurrentSession().update(role);
	}



}
