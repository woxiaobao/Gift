package com.web.serviceimpl;

import java.util.Date;

import com.web.dao.ObjDAO;
import com.web.daoimpl.ObjNumber;
import com.web.entity.User;
import com.web.service.ObjService;

public class UserService implements ObjService {

	
	private ObjDAO userDao;
	private ObjNumber objNumber;

	public void setUserDao(ObjDAO userDao) {
		this.userDao = userDao;
	}
	
	public void setObjNumber(ObjNumber objNumber) {
		this.objNumber = objNumber;
	}


	@Override
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("add user save.");
		User user=((User)obj);
		user.setDateCreated(new Date());
		return userDao.addObj(user);
	}

	@Override
	public boolean updateObj(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("edit user save.");
		User user=((User)obj);
		user.setLastUpdated(new Date());
		return userDao.updateObj(user);
	}

	@Override
	public boolean delObj(String id) {
		// TODO Auto-generated method stub
		return userDao.delObj(id);
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return objNumber.getNumber("from User");
	}

	@Override
	public Object getObj(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
