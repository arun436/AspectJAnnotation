package com.arun.service;

import com.arun.beans.Account;
import com.arun.exceptions.InsufficientFundsException;

public interface TransactionService {
	
	public String withdraw(Account account,int wd_amt) throws InsufficientFundsException;

}
