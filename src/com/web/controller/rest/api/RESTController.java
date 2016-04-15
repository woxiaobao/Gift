package com.web.controller.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.common.web.utils.RequestUtils;
import com.web.common.web.utils.ResponseUtils;
import com.web.controller.sysLog.Log;
import com.web.dao.RoleD;
import com.web.entity.Role;

@Controller
@RequestMapping("/restful")
public class RESTController {
	public static Logger LOG = LogManager.getLogger(RESTController.class);
	
	
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
 	public @ResponseBody Object addBook(HttpServletRequest request) {
 		String pams = RequestUtils.printParams(request);
 		
 		LOG.info("RESTController request info : "+pams);
 		//Map<String, Object> params = RequestUtils.getQueryParams(request);
 		JSONObject jsonObject = new JSONObject();
 		jsonObject.put("msg", "添加信息成功！");
 		jsonObject.put("params", pams);
 		return jsonObject;
 	}
	
	
	
	/**
	 * 这个方法使用工具类ResponseUtils
	 * 使用流的形式返回json
	 * URI:	http://localhost:8089/Gift/restful/info/{id}
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/info/{id}", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@Log(operationType="操作类型",operationName="操作名称")
	public void addBook(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		LOG.info("RESTController request info : "+id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "添加信息成功！");
		ResponseUtils.renderJson(response, jsonObject.toJSONString());
	}
		
	@RequestMapping("/text")
	public void toStr(HttpServletRequest request, HttpServletResponse response){
		String text = "success";
		ResponseUtils.renderText(response, text);
	}
	
	@RequestMapping("/toJson")
	public void toJson(HttpServletRequest request, HttpServletResponse response){
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
		ResponseUtils.renderJson(response, json);
	}
	
	
	
	
	public void setRoleDao(RoleD roleDao) {
		this.roleDao = roleDao;
	}
	

}
