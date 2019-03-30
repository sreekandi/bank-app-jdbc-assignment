package com.capgemini.bankapp.service;

import java.util.List;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountService {
	
	public double checkBalance(long accountId) throws BankAccountNotFoundException;
	
	public double withdraw(long accountId,double accountBalance)throws LowBalanceException, BankAccountNotFoundException;
	
	public double deposit(long accountId,double accountBalance) throws BankAccountNotFoundException;
	
	public boolean deleteBankAccocunt(long accountId) throws BankAccountNotFoundException;
	
	public double fundTransfer(long fromAccount,long toAccount,double accountBalance) throws LowBalanceException, BankAccountNotFoundException;
	
	public boolean addNewAccount(BankAccount account);
	
	public List<BankAccount> findAllBankAccounts();
	
	public BankAccount searchBankAccount(long accountId) throws BankAccountNotFoundException;
	
	public boolean updateBankAccount(String accountHolderName,String accountType);

	double withdrawForFundTransfer(long accountId, double accountBalance)throws LowBalanceException, BankAccountNotFoundException;
	
	
	
}
