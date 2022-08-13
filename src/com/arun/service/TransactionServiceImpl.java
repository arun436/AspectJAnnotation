package com.arun.service;

import org.springframework.stereotype.Component;

import com.arun.beans.Account;
import com.arun.exceptions.InsufficientFundsException;

@Component("transaction")
public class TransactionServiceImpl implements TransactionService {

	@Override
	public String withdraw(Account account, int wd_amt) throws InsufficientFundsException {
		String status = "";
		if(account.getBalance() >= wd_amt) {
			int totalBalance = account.getBalance() - wd_amt;
			account.setBalance(totalBalance);
			System.out.println("From withdraw(): Transaction withdraw completed");
			status = "Success";
		} else {
			status = "Failure";
			throw new InsufficientFundsException("Funds are not sufficient in your account");
		}
		
		return status;
	}

}
