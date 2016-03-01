package com.web.dao;

public interface RoleD extends ObjDAO {

	/**
	 * role接口
	 * @author lvbaolin 2015-07-08
	 *
	 */
	
	/**
	 * 删除一对多外键
	 * @return
	 */
	public void delSetModule(String id);
}
