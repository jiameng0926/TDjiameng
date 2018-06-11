package com.jm.controller;

import com.baomidou.mybatisplus.toolkit.ArrayUtils;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Configuration
public class WebLogControllerAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogControllerAspect.class);
	@Pointcut("execution(public * com.jm.controller.*.*(..))")
	public void pointCut(){}

	@Before(value = "pointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("请求类型 : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("方法路径 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		//logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
		Object[] args = joinPoint.getArgs();
		String[] fieldsName = getFieldsName(className, methodName);
		logParam(fieldsName,args);
	}
	private String[] getFieldsName(String class_name, String method_name) throws Exception {
		Class<?> clazz = Class.forName(class_name);
		String clazz_name = clazz.getName();
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(clazz);
		pool.insertClassPath(classPath);

		CtClass ctClass = pool.get(clazz_name);
		CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
		MethodInfo methodInfo = ctMethod.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if(attr == null){
			return null;
		}
		String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
		int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
		for (int i=0;i<paramsArgsName.length;i++){
			paramsArgsName[i] = attr.variableName(i + pos);
		}
		return paramsArgsName;
	}
	private boolean isPrimite(Class<?> clazz){
		if (clazz.isPrimitive() || clazz == String.class){
			return true;
		}else {
			return false;
		}
	}


	/**
	 * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
	 * @param paramsArgsName    方法参数名数组
	 * @param paramsArgsValue   方法参数值数组
	 */
	private void logParam(String[] paramsArgsName,Object[] paramsArgsValue){
		if(ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)){
			logger.info("该方法没有参数");
			return;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i=0;i<paramsArgsName.length;i++){
			//参数名
			String name = paramsArgsName[i];
			//参数值
			Object value = paramsArgsValue[i];
			buffer.append(name +" = ");
			if(value!=null){
				if(isPrimite(value.getClass())){
					buffer.append(value + "  ,");
				}else {
					buffer.append(value.toString() + "  ,");
				}
			}
		}
		if(buffer.length()>0){
			buffer.deleteCharAt(buffer.length()-1);
		}
		logger.info("参数 : " +buffer.toString());
	}
}
