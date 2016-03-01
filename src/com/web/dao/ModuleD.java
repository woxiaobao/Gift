package com.web.dao;

import java.util.List;

import com.web.entity.Module;

public interface ModuleD extends ObjDAO{

	/**
	 * module接口
	 * @author lvbaolin 2015-07-08
	 *
	 */
	
	/**
	 * module tree 获取子模块
	 * @param parentId
	 * @return
	 */
	List<Module> getModuleChildren(long parentId);

}
