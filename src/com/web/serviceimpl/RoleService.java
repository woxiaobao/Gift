package com.web.serviceimpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.web.dao.ModuleD;
import com.web.dao.ObjDAO;
import com.web.daoimpl.ObjNumber;
import com.web.entity.Module;
import com.web.entity.Role;
import com.web.service.RoleS;

public class RoleService implements RoleS {

	
	private ObjDAO roleDao;
	private ModuleD moduleDao;
	private ObjNumber objNumber;

	public void setRoleDao(ObjDAO roleDao) {
		this.roleDao = roleDao;
	}
	
	public void setModuleDao(ModuleD moduleDao) {
		this.moduleDao = moduleDao;
	}

	public void setObjNumber(ObjNumber objNumber) {
		this.objNumber = objNumber;
	}

	@Override
	public boolean addObj(Role role, String mid) {
		// TODO Auto-generated method stub
		Set<Module> setModule=new HashSet<Module>();
		if(mid!=""){
			String[] moduleid=mid.split(",");
			System.out.println(moduleid.length);
			for(int i=0;i<moduleid.length;i++){
				Module module=(Module) moduleDao.getIdObj(Long.parseLong(moduleid[i]));
				setModule.add(module);
			}
		}
		role.setDateCreated(new Date());
		role.setSetModule(setModule);
		return roleDao.addObj(role);
	}
	
	@Override
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("add role save.");
		Role role=((Role)obj);
		role.setDateCreated(new Date());
		return roleDao.addObj(role);
	}

	@Override
	public boolean updateObj(Role role, String mid) {
		// TODO Auto-generated method stub
		Set<Module> setModule=new HashSet<Module>();
		String[] moduleid=mid.split(",");
		for(int i=0;i<moduleid.length;i++){
			Module module=(Module) moduleDao.getIdObj(Long.parseLong(moduleid[i]));
			setModule.add(module);
		}
		Role r=(Role) roleDao.getIdObj(role.getId());
		r.setRoleName(role.getRoleName());
		r.setComment(role.getComment());
		r.getSetModule().removeAll(r.getSetModule());
		r.setSetModule(setModule);
		r.setLastUpdated(new Date());
		return roleDao.updateObj(r);
	}
	
	@Override
	public boolean updateObj(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("edit role save.");
		Role role=((Role)obj);
		role.setLastUpdated(new Date());
		return roleDao.updateObj(role);
	}

	@Override
	public boolean delObj(String id) {
		// TODO Auto-generated method stub
		return roleDao.delObj(id);
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return objNumber.getNumber("from Role");
	}

	@Override
	public Object getObj(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
