package com.InternsManagement.Utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.InternsManagement.Exception.InternsManagementException;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Log LOGGER=LogFactory.getLog(LoggingAspect.class);
	
	
	@AfterThrowing(pointcut="execution(* com.InternsManagement.Service.ProjectAllocationServiceImpl.*(..))", throwing="exception")
	public void logServiceException(InternsManagementException exception) {
		LOGGER.info(exception.getMessage(), exception);
	}

}
