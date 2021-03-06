package com.capgemini.bankapp.dao;

import java.util.List;

import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountDao {
	
	
	public  double getBalance(long accountId);
	public void updateBalance(long accountId,double newBalance);
	public boolean deleteBankAccount(long accountId);
	public boolean addNewAccount(BankAccount account);

	public List<BankAccount> findAllBankAccounts();
	public BankAccount searchBankAccount(long accountId);
	public boolean updateBankAccount(String accountHolderName, String accountType);
}
