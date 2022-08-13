package com.arun.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.arun.beans.Account;
import com.arun.exceptions.InsufficientFundsException;


@Component("aspect")
@Aspect
public class TransactionAspect {
	
	@Before("execution(* com.arun.service.TransactionService.*(..))")
	public void before(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Account account = (Account)args[0];
		System.out.println("Before Advice Initial Balance :" + account.getBalance());
	}
	@After("execution(* com.arun.service.TransactionService.*(..))")
	public void after(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Account account = (Account)args[0];
		System.out.println("After Advice Total Balance :" + account.getBalance());
	}
	@AfterReturning(pointcut = "execution(* com.arun.service.TransactionService.*(..))", returning = "results")
	public void afterReturning(JoinPoint jp, String results) {
		System.out.println("After returning Transaction Status :" + results);
	}
	
	@Around("execution(* com.arun.service.TransactionService.*(..))")
	public void around(ProceedingJoinPoint jp) {
		System.out.println("Around Advice Before : " + jp.getSignature().getName() + " Method Execution");
		String status = "";
		try {
			status = (String)jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("Around Advice After : " + jp.getSignature().getName() + " Method Execution");
		System.out.println("Around Advice : Transaction status : " + status);
	}
	
	@AfterThrowing(pointcut = "execution(* com.arun.service.TransactionService.*(..))", throwing = "exception")
	public void afterThrowing(JoinPoint jp, InsufficientFundsException exception) {
		System.out.println("After throwing advice : " + exception.getClass().getName() + " In Transaction " + exception.getMessage());
		
	}
}
