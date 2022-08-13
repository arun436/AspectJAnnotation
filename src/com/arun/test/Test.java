package com.arun.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arun.beans.Account;
import com.arun.exceptions.InsufficientFundsException;
import com.arun.service.TransactionService;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/arun/resources/applicationContext.xml");
		Account account = (Account)context.getBean("accBean");
		TransactionService tService = (TransactionService)context.getBean("transaction");
		try {
			tService.withdraw(account, 10000);
		} catch (InsufficientFundsException e) {
			// TODO: handle exception
		}
	}
}
