package com.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dao.ModuleD;
import com.web.entity.Module;
import com.web.service.ModuleS;
import com.web.util.Page;
import com.web.util.PageUtil;

/**
 * 
 * @author lvbaolin 2015-06-18
 *
 */

@Controller
@RequestMapping("/module")
public class ModuleController {
	public static Logger LOG = LogManager.getLogger(ModuleController.class);
	@Resource(name = "moduleService")
	private ModuleS moduleService;
	@Resource(name = "moduleDao")
	private ModuleD moduleDao;
	
	/**
	 * 到列表页
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toList")
	public String toList(HttpServletRequest request) {
		LOG.info("ModuleController received the new message : ");
		//Page page=getPageAll("1");//默认到第一页
		//Page page=new Page();
		List<Module> moduleList=(List<Module>)moduleDao.getAll();
		request.setAttribute("moduleList", moduleList);
		request.setAttribute("dtree", moduleList);
		//request.setAttribute("page", page);
		return "/admin/module_list";
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
		List<Module> moduleList=(List<Module>)moduleDao.getAll(page);
		request.setAttribute("moduleList", moduleList);
		request.setAttribute("page", page);
		return "/admin/module_list";
	}
	
	/**
	 * 私有方法，传入当前页，返回Page对象
	 * @param currentPage
	 * @return
	 */
	private Page getPageAll(String currentPage){
		Page page=new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalCount(moduleService.getNumber());
		page.setEveryPage(page.getTotalCount());
		page=PageUtil.createPage(page.getEveryPage(), page.getTotalCount(), page.getCurrentPage());
		return page;
	}
	
	/**
	 * 到新增页
	 * @param sid 新增module的parentId
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(int sid, HttpServletRequest request) {
		request.setAttribute("sid", sid);
		return "/admin/module_add";
	}
	
	/**
	 * 到编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(String id, HttpServletRequest request){
		Module module=(Module) moduleDao.getIdObj(Long.parseLong(id));
		request.setAttribute("module", module);
		return "/admin/module_edit";
	}
	
	/**
	 * 新增进行保存
	 * @param module
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addObj")
	public String addModule(Module module,HttpServletRequest request, HttpServletResponse response) {
		LOG.info("模块名称"+module.getName());
		moduleService.addObj(module);
		return toList(request);
	}
	
	/**
	 * 编辑保存
	 * @param module
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/editObj")
	public String editObj(Module module,HttpServletRequest request, HttpServletResponse response) {
		LOG.info(module.getId());
		LOG.info(module.getName());
		moduleService.updateObj(module);
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
		if (moduleService.delObj(Long.parseLong(id))) {
			return "/massege/success";
		}else{
			return "/massege/error";
		}
	}
	
	/**
	 * 点击模块tree
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toModule")
	public String toModule(String id, HttpServletRequest request, HttpServletResponse response){
		if(id.equals("")){
			return "/massege/error";
		}
		Module module=(Module) moduleDao.getIdObj(Long.parseLong(id));
		List<Module> moduleList= moduleDao.getModuleChildren(Long.parseLong(id));
		request.setAttribute("sid", Long.parseLong(id));
		request.setAttribute("module", module);
		request.setAttribute("mlist", moduleList);
		return "/admin/module_list_children";
	}

	public void setModuleService(ModuleS moduleService) {
		this.moduleService = moduleService;
	}

	public void setModuleDao(ModuleD moduleDao) {
		this.moduleDao = moduleDao;
	}

	
}
