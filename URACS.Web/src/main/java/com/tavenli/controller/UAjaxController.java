package com.tavenli.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tavenli.entity.UserEntity;
import com.tavenli.model.PageData;
import com.tavenli.services.UCenterService;
import com.tavenli.utils.PageNavUtil;

@Controller
@RequestMapping("/u")
public class UAjaxController extends UBaseController {

	private static Logger logger = LoggerFactory.getLogger(UAjaxController.class);
	
	@Autowired
	private UCenterService uCenterService;
	
	@RequestMapping("/queryUsers")
	@ResponseBody
	public Map<String, Object> queryUsers(Model model,Integer page,String userName){
		boolean resultStatus = true;
		page = page== null ? 1 : page<1 ? 1 : page;
				
		int pageSize = 10;
		
		PageData<UserEntity> pageData = this.uCenterService.getUsers(page, pageSize, userName);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("datas", pageData.getPageData());
		resMap.put("totalCount", pageData.getTotalCount());
		resMap.put("totalPage", pageData.getTotalPage());
		resMap.put("currentPage", page);
		resMap.put("pageNav", PageNavUtil.getAjaxPageNavHtml(page.intValue(), pageSize, pageData.getTotalCount(), 15));
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	@RequestMapping(value = "/delUsers", method = RequestMethod.POST)
	@ResponseBody
	public int delUsers(int[] userIds) {

		
		return 0;
	}
}
