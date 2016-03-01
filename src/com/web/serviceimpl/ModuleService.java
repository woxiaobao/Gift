package com.web.serviceimpl;

import com.web.dao.ObjDAO;
import com.web.daoimpl.ObjNumber;
import com.web.service.ModuleS;

public class ModuleService implements ModuleS {
	/**
	 * Module服务接口
	 * @author lvbaolin
	 *
	 */
	
	private ObjDAO moduleDao;
	private ObjNumber objNumber;

	public void setModuleDao(ObjDAO moduleDao) {
		this.moduleDao = moduleDao;
	}
	
	public void setObjNumber(ObjNumber objNumber) {
		this.objNumber = objNumber;
	}


	@Override
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("add module save.");
		return moduleDao.addObj(obj);
	}

	@Override
	public boolean updateObj(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("edit module save.");
		return moduleDao.updateObj(obj);
	}

	@Override
	public boolean delObj(String id) {
		// TODO Auto-generated method stub
		return moduleDao.delObj(id);
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return objNumber.getNumber("from Module");
	}

	@Override
	public Object getObj(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delObj(long id) {
		// TODO Auto-generated method stub
		return moduleDao.delObj(id);
	}

}
