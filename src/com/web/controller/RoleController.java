package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.web.dao.ModuleD;
import com.web.dao.RoleD;
import com.web.entity.Module;
import com.web.entity.Role;
import com.web.service.RoleS;
import com.web.util.Page;
import com.web.util.PageUtil;

@Controller
@RequestMapping("/role")
public class RoleController {

	public static Logger LOG = LogManager.getLogger(RoleController.class);
	@Resource(name = "roleService")
	private RoleS roleService;
	@Resource(name = "roleDao")
	private RoleD roleDao;
	@Resource(name = "moduleDao")
	private ModuleD moduleDao;
	
	
	@RequestMapping("/printd")
    public String printd(HttpServletRequest request){
        String message = "Hello World, Spring MVC!";
       
		return toJSONData(request,message);
    }
	/**
	 * 获取json
	 * @param request
	 * @return
	 */
	@RequestMapping("/json")
	public String json(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<Role> roleList=(List<Role>) roleDao.getAll();
		if(roleList.size()<1){
			return null;
		}
		List<Role> list=new ArrayList<Role>();
		Map<String,List<?>> roleMap=new HashMap<String,List<?>>();
		for(Role r:roleList){
			Role ro=new Role();
			ro.setId(r.getId());
			ro.setRoleName(r.getRoleName());
			list.add(ro);
		}
		roleMap.put("items", list);
		String json = JSON.toJSONString(roleMap,true);
		return toJSONData(request,json);
	}
	
	private String toJSONData(HttpServletRequest request,String json){
		request.setAttribute("json", json);
		return "/massege/toJson";
		
	}
	
	/**
	 * 获取json
	 * @param request
	 * @return
	 */
	@RequestMapping("/toJson")
	public String toJson(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<Role> roleList=(List<Role>) roleDao.getAll();
		Map<String,Object> roleMap=new HashMap<String,Object>();
		int i=0;
		for(Role r:roleList){
			Role ro=new Role();
			ro.setId(r.getId());
			ro.setRoleName(r.getRoleName());
			roleMap.put("items"+i, ro);
			i++;
			
		}
		String json = JSON.toJSONString(roleMap,true); 
		request.setAttribute("json", json);
	    System.out.println(json);
		return "/massege/toJson";
	}
	
	/**
	 * 到列表页
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toList")
	public String toList(HttpServletRequest request) {
		LOG.info("RoleController received the new message : ");
		Page page=getPageAll("1");//默认到第一页
		List<Role> roleList=(List<Role>)roleDao.getAll(page);
		request.setAttribute("roleList", roleList);
		request.setAttribute("page", page);
		return "/admin/role_list";
	}
	
	/**
	 * 点击分页到列表页
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toPageList")
	public String toPageList(String currentPage,HttpServletRequest request) {
		Page page=getPageAll(currentPage);
		List<Role> roleList=(List<Role>)roleDao.getAll(page);
		request.setAttribute("roleList", roleList);
		request.setAttribute("page", page);
		return "/admin/role_list";
	}
	
	/**
	 * 私有方法，传入当前页，返回Page对象
	 * @param currentPage
	 * @return
	 */
	private Page getPageAll(String currentPage){
		Page page=new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalCount(roleService.getNumber());
		page=PageUtil.createPage(page.getEveryPage(), page.getTotalCount(), page.getCurrentPage());
		return page;
	}
	
	/**
	 * 到新增页
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Module> moduleList=(List<Module>)moduleDao.getAll();
		request.setAttribute("dtree", moduleList);
		return "/admin/role_add";
	}
	
	/**
	 * 到编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(String id, HttpServletRequest request){
		Role role=(Role) roleDao.getIdObj(id);
		@SuppressWarnings("unchecked")
		List<Module> moduleList=(List<Module>)moduleDao.getAll();
		request.setAttribute("dtree", moduleList);
		request.setAttribute("role", role);
		request.setAttribute("roleModule", role.getSetModule());
		return "/admin/role_edit";
	}
	
	/**
	 * 新增进行保存
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addObj")
	public String addRole(Role role,HttpServletRequest request, HttpServletResponse response) {
		String mid=request.getParameter("mid");
		roleService.addObj(role,mid);
		return toList(request);
	}
	
	/**
	 * 编辑保存
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/editObj")
	public String editObj(Role role,HttpServletRequest request, HttpServletResponse response) {
		String mid=request.getParameter("mid");
		//roleDao.delSetModule(role.getId());
		roleService.updateObj(role,mid);
		return toList(request);
	}

	/**
	 * 删除方法
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delObj")
	public String delObj(String id, HttpServletRequest request, HttpServletResponse response) {
		if(id.equals("")){
			return "/massege/error";
		}
		if (roleService.delObj(id)) {
			return "/massege/success";
		}else{
			return "/massege/error";
		}
	}

	public void setRoleService(RoleS roleService) {
		this.roleService = roleService;
	}

	public void setRoleDao(RoleD roleDao) {
		this.roleDao = roleDao;
	}

	public void setModuleDao(ModuleD moduleDao) {
		this.moduleDao = moduleDao;
	}
	
	
}
