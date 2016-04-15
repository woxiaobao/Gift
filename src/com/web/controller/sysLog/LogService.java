package com.web.controller.sysLog;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

/**
 * \
 * 
 * @Aspect 实现spring aop 切面（Aspect）
 * @author lvbaolin
 * 
 */

@Component
@Aspect
public class LogService implements ThrowsAdvice{
	public static Logger LOG = LogManager.getLogger(LogService.class);

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.web.controller..*(..))")
	public void aspect() {
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
			LOG.info("before " + joinPoint);
	}

	//配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint) {
		try {
				LOG.info("after " + joinPoint);
			String targetName = joinPoint.getTarget().getClass().getName();  
            String methodName = joinPoint.getSignature().getName();  
            Object[] arguments = joinPoint.getArgs();  
            @SuppressWarnings("rawtypes")
			Class targetClass = Class.forName(targetName);  
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
             for (Method method : methods) {  
                 if (method.getName().equals(methodName)) {  
                    @SuppressWarnings("rawtypes")
					Class[] clazzs = method.getParameterTypes();  
                     if (clazzs.length == arguments.length) {  
                         operationType = method.getAnnotation(Log.class).operationType();
                         operationName = method.getAnnotation(Log.class).operationName();
                         break;  
                    }  
                }  
            }
             LOG.info("after " + operationName);
             LOG.info("after " + operationType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	/*
     * 环绕通知就类似Filter,在一个方法中包含开始,执行,结束,抛出异常 ,
     * 甚至可以不调用joinPoint的proceed方法执行真实逻辑 亦可以多次调用
     * ,全由开发者业务逻辑决定
     */
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) {
		//long start = System.currentTimeMillis();
		LOG.info("around " + joinPoint );
		try {
			return ((ProceedingJoinPoint) joinPoint).proceed();
		} catch (Throwable e) {
			return "error";
		}
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint) {
			LOG.info("afterReturn " + joinPoint);
	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
			LOG.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
	}

}
