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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
	
	
	
	
	// URI:	http://localhost:8089/Gift/restful/hi
	@RequestMapping(value = "/hi", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hello() {
		//logger.info("测试hi");
		return "Hello World !!!";
	}
	
	// URI:	http://localhost:8080/SpringMVC-RESTful-Json/say/hello world
	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody String say(@PathVariable("msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}
	
	//POST
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public @ResponseBody Object addBook( ) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "添加信息成功！");
		return jsonObject;
	}
		
	/**
	 * 测试使用
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/${id}",produces="application/json;charset=UTF-8")
	public @ResponseBody String  get(@PathVariable int id){
//		Page page=getPageAll("1");//默认到第一页
//		List<User> userList=(List<User>)userDao.getAll(page);
		return ((User) userDao.getIdObj(id+"")).toString();
	}
	
	@RequestMapping("/toJson")
	public @ResponseBody String toJson(HttpServletRequest request){
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
		//request.setAttribute("json", json);
	    //System.out.println(json);
		return json;
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
