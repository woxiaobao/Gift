package com.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface ObjService {
	
	
	public static Logger LOG = LogManager.getLogger(ObjService.class);
	
	
	
	/**
	 * 业务处理新增时
	 * @param obj
	 * @return
	 */
	public boolean addObj(Object obj);

	/**
	 * 通过id获取信息
	 * @param id
	 * @return
	 */
	public Object getObj(String id);
	/**
	 * 业务处理删除时
	 * @param id
	 * @return
	 */
	public boolean delObj(String id);

	/**
	 * 业务处理更新时
	 * @param obj
	 * @return
	 */
	public boolean updateObj(Object obj);
	
	/**
	 * 获取对象的总数量
	 * @return
	 */
	public int getNumber();

}
