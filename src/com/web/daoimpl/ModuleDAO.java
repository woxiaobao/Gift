package com.web.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.web.dao.ModuleD;
import com.web.entity.Module;
import com.web.util.Page;

public class ModuleDAO implements ModuleD {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void getTest(){
		
	}
	/**
	 * 获取子模块
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getModuleChildren(long parentId){
		String hql="from Module where parentId='"+parentId+"'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save((Module)obj);
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
		Query query = sessionFactory.getCurrentSession().createQuery("from Module");  
        //设置每页显示多少个，设置多大结果。  
        query.setMaxResults(page.getEveryPage());  
        //设置起点  
        query.setFirstResult(page.getBeginIndex());  
        return query.list();  
	}

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Module").list();
	}
	
	@Override
	public Object getIdObj(String id) {
		// TODO Auto-generated method stub
//		Object module=sessionFactory.getCurrentSession().get(Module.class, id);
		return sessionFactory.getCurrentSession().get(Module.class, id);
	}

	@Override
	public Object getIdObj(Long id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Module.class, id);
	}
	
	@Override
	public boolean updateObj(Object obj) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update((Module)obj);
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
			Module module=(Module) getIdObj(id);
			sessionFactory.getCurrentSession().delete(module);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delObj(Long id) {
		// TODO Auto-generated method stub
		try {
			Module module=(Module) getIdObj(id);
			sessionFactory.getCurrentSession().delete(module);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	

}
