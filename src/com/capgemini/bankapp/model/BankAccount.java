package com.capgemini.bankapp.model;

public class BankAccount {

	private long accountId;
	private String accountHolderName;
	private String accountType;
	private double accountBalance;

	public BankAccount() {
		super();

	}

	public BankAccount(long accountId, double accountBalance) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
	}

	public BankAccount(String accountHolderName, String accountType, double accountBalence) {
		super();
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.accountBalance = accountBalence;
	}

	public BankAccount(long accountId, String accountHolderName, String accountType, double accountBalance) {
		super();
		this.accountId = accountId;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalence(double accountBalence) {
		this.accountBalance = accountBalence;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", accountHolderName=" + accountHolderName + ", accountType="
				+ accountType + ", accountBalance=" + accountBalance + "]";
	}

}
