package com.web.dao;

import java.util.List;

import com.web.util.Page;

public interface ObjDAO {
	/**
	 * DAO总接口
	 * @author lvbaolin
	 *
	 */
	
	/**
	 * 新增
	 * @param obj 代表所有对象
	 * @return
	 */
	public boolean addObj(Object obj);

	/**
	 * 分页获取信息/所有
	 * @param nowPage 页码数值
	 * @return
	 */
	public List<?> getAll(Page page);
	
	public List<?> getAll();
	/**
	 * 删除某一条信息
	 * @param id 对象的ID
	 * @return
	 */
	public boolean delObj(String id);
	
	public boolean delObj(Long id);

	/**
	 * 获取某一条数据
	 * @param l 对象的ID
	 * @return
	 */
	public Object getIdObj(String id);

	/**
	 * 获取某一条数据
	 * @param id 对象的ID
	 * @return
	 */
	public Object getIdObj(Long id);
	/**
	 * 数据更新
	 * @param obj 更新对象
	 * @return
	 */
	public boolean updateObj(Object obj);

	
	
}
