package com.web.controller.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.dao.RoleD;
import com.web.dao.UserD;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.service.ObjService;

@Controller
@RequestMapping("/restful")
public class RESTController {
	
	@Resource(name = "userService")
	private ObjService userService;
	@Resource(name = "userDao")
	private UserD userDao;
	@Resource(name = "roleDao")
	private RoleD roleDao;
	
	/**
	 * 测试使用
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/${id}",produces="application/json;charset=UTF-8")
	public User  get(@PathVariable int id){
//		Page page=getPageAll("1");//默认到第一页
//		List<User> userList=(List<User>)userDao.getAll(page);
		return (User) userDao.getIdObj(id+"");
	}
	
	@RequestMapping("/toJson")
	public String toJson(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<Role> roleList=(List<Role>) roleDao.getAll();
		Map<String,List<Role>> roleMap=new HashMap<String,List<Role>>();
		List<Role> list = new ArrayList<Role>();
		for(Role r:roleList){
			Role ro=new Role();
			ro.setId(r.getId());
			ro.setRoleName(r.getRoleName());
			list.add(ro);
		}
		roleMap.put("item", list);
		String json = JSON.toJSONString(roleMap,true); 
		request.setAttribute("json", json);
	    //System.out.println(json);
		return "/massege/toJson";
	}
	
	
	public void setUserService(ObjService userService) {
		this.userService = userService;
	}
	public void setUserDao(UserD userDao) {
		this.userDao = userDao;
	}
	public void setRoleDao(RoleD roleDao) {
		this.roleDao = roleDao;
	}
	
	
	

}
