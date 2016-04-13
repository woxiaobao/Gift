package com.web.controller;

import java.util.List;

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

import com.web.dao.RoleD;
import com.web.dao.UserD;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.service.ObjService;
import com.web.util.Page;
import com.web.util.PageUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	public static Logger LOG = LogManager.getLogger(UserController.class);
	@Resource(name = "userService")
	private ObjService userService;
	@Resource(name = "userDao")
	private UserD userDao;
	@Resource(name = "roleDao")
	private RoleD roleDao;
	/**
	 * @Resource注解默认按名称装配。
	 * 如果指定name属性  只能按名称装配
	 */
	
	
	/**
	 * 测试使用
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/get/${id}", method = RequestMethod.GET)
	public User  get(@PathVariable int id){
		//System.out.println(request.getParameter("id"));
		//System.out.println(request.getParameter("data"));
//		Page page=getPageAll("1");//默认到第一页
//		List<User> userList=(List<User>)userDao.getAll(page);
//		return new ModelAndView("success");
		//User user=(User) userDao.getIdObj(id);
		return (User) userDao.getIdObj(id+"");
	}
	/**
	 * 到列表页
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toList")
	public String toList(HttpServletRequest request) {
		LOG.info("UserController received the new message : ");
		Page page=getPageAll("1");//默认到第一页
		List<User> userList=(List<User>)userDao.getAll(page);
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		return "/admin/user_list";
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
		List<User> userList=(List<User>)userDao.getAll(page);
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		return "/admin/user_list";
	}
	
	/**
	 * 私有方法，传入当前页，返回Page对象
	 * @param currentPage
	 * @return
	 */
	private Page getPageAll(String currentPage){
		Page page=new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalCount(userService.getNumber());
		page=PageUtil.createPage(page.getEveryPage(), page.getTotalCount(), page.getCurrentPage());
		return page;
	}
	
	/**
	 * 到新增页
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "/admin/user_add";
	}
	
	/**
	 * 到编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(String id, HttpServletRequest request){
		User user=(User) userDao.getIdObj(id);
		request.setAttribute("user", user);
		return "/admin/user_edit";
	}
	
	/**
	 * 新增进行保存
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addObj")
	public String addUser(User user,HttpServletRequest request, HttpServletResponse response) {
		String rid=request.getParameter("roleid");
		Role role=(Role) roleDao.getIdObj(rid);
		user.setRole(role);
		userService.addObj(user);
		return toList(request);
	}
	
	/**
	 * 编辑保存
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/editObj")
	public String editObj(User user,HttpServletRequest request, HttpServletResponse response) {
		String rid=request.getParameter("roleid");
		Role role=(Role) roleDao.getIdObj(rid);
		user.setRole(role);
		userService.updateObj(user);
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
		if (userService.delObj(id)) {
			return "/massege/success";
		}else{
			return "/massege/error";
		}
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
