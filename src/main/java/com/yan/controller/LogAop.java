package com.yan.controller;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yan.domain.SysLog;
import com.yan.service.impl.SysLogService;

@Component
@Aspect
public class LogAop { 
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SysLogService sysLogService;
	
	private Date visitTime; //开始访问的时间
	private Class clazz;//访问的类
	private Method method;//访问的方法
	//前置通知,主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
	@Before("execution(* com.yan.controller.*.*(..))")
	public void doBefore(JoinPoint jp) throws NoSuchMethodException, SecurityException {
		visitTime = new Date(); //开始时间就是开始访问的时间
		clazz = jp.getTarget().getClass(); //具体要访问的类
		String methodName= jp.getSignature().getName(); //获取访问方法的名称
		Object[] args = jp.getArgs();
		
		//获取具体执行的方法method的对象
		if(args==null||args.length==0) {
			method= clazz.getMethod(methodName);
		}else {
			Class[] classArgs = new Class[args.length];
			for(int i =0;i<args.length;i++) {
				classArgs[i] = args[i].getClass();
			}
			clazz.getMethod(methodName, classArgs);
		}
		
	}
	
	//后置通知
	@After("execution (* com.yan.controller.*.*(..))")
	public void doAfter(JoinPoint jp) throws NoSuchMethodException, SecurityException {
		long time = new Date().getTime() - visitTime.getTime();//获取访问的时长
		
		String url= "";
		//获取url
		if(clazz!=null&&method!=null&&clazz!=LogAop.class) {
			//1.获取类上的@RequestMapping("/orders")
			RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
			if(classAnnotation!=null) {
				String[] classValue = classAnnotation.value();
				//2.获取方法上的@Requestmapping("/xxx")
				RequestMapping methodAnnotation = (RequestMapping)method.getAnnotation(RequestMapping.class);
				if(methodAnnotation!=null) {
					String[] methodValue = methodAnnotation.value();
					
					url = classValue[0]+methodValue[0];
				}
			}
		}
		//获取访问的IP
		String ip = request.getRemoteAddr();
		
		//获取当前操作的用户
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User)context.getAuthentication().getPrincipal();
		String username = user.getUsername();
		
		 //将日志相关信息封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time); //执行时长
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //调用Service完成操作
        sysLogService.save(sysLog);
	}
}
