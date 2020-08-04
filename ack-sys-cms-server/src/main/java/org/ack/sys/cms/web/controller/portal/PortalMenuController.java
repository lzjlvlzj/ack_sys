package org.ack.sys.cms.web.controller.portal;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.service.portal.PortalMenuService;
import org.ack.sys.cms.web.util.WebUtil;
import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 导航菜单
 * 
 * @author ack
 *
 */
@Controller
@RequestMapping("/portal/menu")
public class PortalMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortalMenuController.class);
	
	@Autowired
	private PortalMenuService portalMenuServiceImpl;

	@AckPermission("portal:menu:add")
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated PortalMenu portalMenu,
								 BindingResult result, HttpServletRequest request){
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			portalMenu.setDeleteStatus(0);
			int r = portalMenuServiceImpl.insert(portalMenu);
			int code = 500;
			String msg = "";
			Object data = null;
			if (r == 1) {
				code = 200;
				msg = "";
				data = r;
			} else if(r == -1) {
				code = 400;
				msg = "菜单已存在";
				data = r;
			}
			return new ResponseResult(code, msg, data);
		}
	}

	@AckPermission("portal:menu:edit")
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult update(@RequestBody PortalMenu portalMenu, HttpServletRequest request, HttpServletResponse response){
		int r = portalMenuServiceImpl.update(portalMenu);
		int code = 500;
		String msg = "";
		Object data = null;
		if (r == 1) {
			code = 200;
			msg = "";
			data = r;
		} else if(r == -1) {
			code = 400;
			msg = "参数错误";
			data = r;
		}
		return new ResponseResult(code, msg, data);
	}

	@AckPermission("portal:menu:delete")
	@DeleteMapping("/edit")
	@ResponseBody
	public ResponseResult delete(@RequestBody PortalMenu portalMenu, HttpServletRequest request, HttpServletResponse response){
		int code = 200;
		String msg = "";
		int rt = portalMenuServiceImpl.delete(portalMenu);
		if (rt != 1) {
			code = 500;
			msg = "删除菜单失败";
		}
		return new ResponseResult(code, msg, rt);
	}

	@AckPermission("portal:menu:add OR portal:menu:edit")
	@PostMapping("/upload")
	@ResponseBody
	public ResponseResult upload(MultipartHttpServletRequest request){
		Map<String, MultipartFile> map = request.getFileMap();
		int size = map.size();
		if(size == 0){
			return new ResponseResult(400, "上传文件不能为空", null);
		}
		Set<String> set = map.keySet();
		MultipartFile file  = null;
		for(String key : set){
			file = map.get(key);
			break;
		}
		ResponseResult result = portalMenuServiceImpl.upload(file);
		return result;
	}

	@PostMapping("/findPage")
	@AckPermission("portal:menu:view")
	@ResponseBody
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		logger.debug("查询门户菜单");
		pageRequest.setOrderColumn("createTime");
		Page<PortalMenu> page = portalMenuServiceImpl.findPage(pageRequest);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}

	@GetMapping("/findAll")
	@AckPermission("portal:menu:view OR portal:article:add")
	@ResponseBody
	public ResponseResult findAllPortalMenu(){
		List<PortalMenu> list = portalMenuServiceImpl.findAll();
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}
	
	
	

}
