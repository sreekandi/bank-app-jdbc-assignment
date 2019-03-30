package com.capgemini.bankapp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.util.DbUtil;

public class BankAccountServiceImpl  implements BankAccountService{
	
	public BankAccountDao bankAccountDao;
	static final Logger logger = Logger.getLogger(BankAccountServiceImpl.class);	
	

	public BankAccountServiceImpl() {
		bankAccountDao = new BankAccountDaoImpl();
	}

	@Override
	public double checkBalance(long accountId)  throws BankAccountNotFoundException{
		double balance=  bankAccountDao.getBalance(accountId);
		
		if(balance>=0)
			return balance;
		throw new BankAccountNotFoundException(" BankAccount Dose't Exist");
	}


	public double withdraw(long accountId, double accountBalance) throws LowBalanceException ,BankAccountNotFoundException{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance<=0)
			throw new BankAccountNotFoundException(" BankAccount Dose't Exist");
		if(balance - accountBalance > 0)
		{
			balance =balance - accountBalance;
			bankAccountDao.updateBalance(accountId, balance);
		
			return balance;
		}
		else
			 throw new LowBalanceException("you don't have sufficient fund");
			
	}
	@Override
	public double withdrawForFundTransfer(long accountId, double accountBalance) throws LowBalanceException ,BankAccountNotFoundException{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance<=0)
			throw new BankAccountNotFoundException(" BankAccount Dose't Exist");
		if(balance - accountBalance > 0)
		{
			balance =balance - accountBalance;
			bankAccountDao.updateBalance(accountId, balance);
			DbUtil.commit();
			return balance;
		}
		else
			 throw new LowBalanceException("you don't have sufficient fund");
			
	}

	@Override
	public double deposit(long accountId, double accountBalance) throws BankAccountNotFoundException {
		double balance =bankAccountDao.getBalance(accountId);
		if(balance<0)
			throw new BankAccountNotFoundException("bank Account Dos't exist");
		balance =balance + accountBalance;
		bankAccountDao.updateBalance(accountId,balance);
		DbUtil.commit();
		return balance;
	}

	@Override
	public boolean deleteBankAccocunt(long accountId) throws BankAccountNotFoundException {
		boolean result = bankAccountDao.deleteBankAccount(accountId);
		if(result) 
		{
			DbUtil.commit();
			return result;
		}
		throw new BankAccountNotFoundException("BankAccount not exist");
	}

	@Override
	public double fundTransfer(long fromAccount, long toAccount, double accountBalance) throws  LowBalanceException ,BankAccountNotFoundException {
		try
		{
			double newbalance =withdraw(fromAccount,toAccount);
			deposit(toAccount,accountBalance);
			DbUtil.commit();
			return newbalance;
		}
		catch(LowBalanceException | BankAccountNotFoundException e)
		{
			logger.error("Exception",e);
			DbUtil.rollback();
			throw e;
		}

	}
	@Override
	public boolean addNewAccount(BankAccount account) {
		boolean result = bankAccountDao.addNewAccount(account);
		if(result)
			DbUtil.commit();
		return result;
	}
	
	@Override
	public List<BankAccount> findAllBankAccounts() {
		return bankAccountDao.findAllBankAccounts();
	}

	@Override
	public BankAccount searchBankAccount(long accountId) throws BankAccountNotFoundException {
		BankAccount bankaccount =  bankAccountDao.searchBankAccount(accountId);
		if(bankaccount!=null)
		{
			return bankaccount;
		}
		throw new BankAccountNotFoundException("Bankaccount not exist");
	}

	@Override
	public boolean updateBankAccount(String accountHolderName, String accountType) {
		boolean result =bankAccountDao.updateBankAccount(accountHolderName, accountType);
		if(result)
			DbUtil.commit();
		return result;
	}
	

}
