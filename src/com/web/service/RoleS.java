package com.web.service;

import com.web.entity.Role;

/**
 * Role服务接口
 * @author lvbaolin 2015-07-09
 *
 */

public interface RoleS extends ObjService {
	
	/**
	 * add
	 * @param role
	 * @param mid module的id拼接
	 * @return
	 */
	public boolean addObj(Role role,String mid);

	public boolean updateObj(Role role, String mid);
}
