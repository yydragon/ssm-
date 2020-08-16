package com.yan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yan.domain.SysLog;
import com.yan.service.impl.SysLogService;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		List<SysLog> sysLogList = sysLogService.findAll();
		mv.addObject("sysLogs",sysLogList);
		mv.setViewName("syslog-list");
		return mv;
		
	}
}
