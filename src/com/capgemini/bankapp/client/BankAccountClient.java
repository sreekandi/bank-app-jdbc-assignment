package com.capgemini.bankapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

public class BankAccountClient {
	
	static final Logger logger = Logger.getLogger(BankAccountClient.class);

	public static void main(String[] args)  {
		
		
		int choice;
		
		long accountId;
		String accountHolderName;
		String accountType;
		double accountBalance;
		double amount;
		long toAccount;
		long fromAccount;

		double balance = 0;
		BankAccountService bankService = new BankAccountServiceImpl();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				System.out.println("1. Add New BankAccount\n2. Withdraw\n3. Deposit\n4. Fund Transfer");
				System.out.println("5.Check Balance \n6. Display All BankAccount Details");
				System.out.println("7. Search BankAccount\n8.Delete BankAccount \n9.UpdateBankAccount\n10Exit\n");

				System.out.print("Please enter your choice  ");
				choice = Integer.parseInt(reader.readLine());

				
				switch (choice) {

				case 1:
					System.out.println("Enter account holder name: ");
					accountHolderName = reader.readLine();
					System.out.println("Enter account type: ");
					accountType = reader.readLine();
					System.out.println("Enter account balance: ");
					accountBalance = Double.parseDouble(reader.readLine());
					BankAccount account = new BankAccount(accountHolderName, accountType, accountBalance);
					
					if (bankService.addNewAccount(account))
					{
						System.out.println("Account created successfully...\n");
					}
					else
					{
						System.out.println("failed to create new account...\n");
					}
					break;
					
				case 2:
					System.out.println("enter account number");
					accountId=Long.parseLong(reader.readLine());
					System.out.println("enter withdraw amount");
					amount=Double.parseDouble(reader.readLine());
					try
					{
					try {
						balance=bankService.withdraw(accountId,amount);
					} catch (BankAccountNotFoundException e) {
						
						e.printStackTrace();
					}
					System.out.println("Amount Withraw" +amount + "account number"+accountId);
					System.out.println("account balance" +balance);
					}
					catch (LowBalanceException e) 
					{
						
						logger.error("Withdraw Exception:" ,e);
					} 
					break;	
				case 3:
					System.out.println("enter account number");
					accountId=Long.parseLong(reader.readLine());
					System.out.println("enter withdraw amount");
					amount=Double.parseDouble(reader.readLine());
					try {
						balance=bankService.deposit(accountId,amount);
					} catch (BankAccountNotFoundException e1) {
						
						e1.printStackTrace();
					}
					System.out.println("Amount deposuited" +amount + "account number"+accountId);
					System.out.println("account balance"+balance); 
					break;
					
				case 4:
					
					System.out.println("to account number");
					fromAccount=Long.parseLong(reader.readLine());
					
					System.out.println("from account number");
					toAccount=Long.parseLong(reader.readLine());
					System.out.println("enter ammount to transfer");
					amount=Double.parseDouble(reader.readLine());
					try
					{
					try {
						balance=bankService.fundTransfer(fromAccount, toAccount, amount);
					} catch (BankAccountNotFoundException e) {
					
						e.printStackTrace();
					}
					System.out.println("from account"+fromAccount + " amountbalance"+bankService.checkBalance(fromAccount));
					System.out.println("to account"+fromAccount + " amountbalance"+bankService.checkBalance(toAccount));
					}
				
				catch(LowBalanceException | BankAccountNotFoundException e)
				{
					logger.error("Fund Transfer Failed:" ,e);
				}
					
					break;
				case 5:
					System.out.println("enter account number");
					accountId=Long.parseLong(reader.readLine());
					System.out.println("account numbber"+accountId + "bankaccount balance"+bankService.checkBalance(accountId));
					break;
				case 6:
					List<BankAccount> bankaccount=new ArrayList<BankAccount>();
					bankaccount=bankService.findAllBankAccounts();
					for(BankAccount accounts : bankaccount)
					{
						System.out.println(accounts);
					}
					break;
					
				case 7:
					System.out.println("seach an account if you want");
					accountId=Long.parseLong(reader.readLine());
					try
					{
					BankAccount accountdetails=bankService.searchBankAccount(accountId);
					System.out.println(accountdetails);
					}
					catch(BankAccountNotFoundException e)
					{
						logger.error("Exception",e);
					}
					
					break;
					
				case 8:
					System.out.println("delete account which account u want to delete");
					accountId=Long.parseLong(reader.readLine());
					if(bankService.deleteBankAccocunt(accountId))
					{
						System.out.println("account deleted");
					}
					else
					{
						System.out.println("account was not deleted");
					}
					break;
				case 9:
					System.out.println("Enter account holder name: ");
					accountHolderName = reader.readLine();
					System.out.println("Enter account type: ");
					accountType = reader.readLine();
					
					if(bankService.updateBankAccount(accountHolderName, accountType))
					{
						System.out.println("sucessfully updated bank account");
					}
					else
					{
						System.out.println("failed to update bankaccount");
					}
					
				case 10:
					System.out.println("Thanks for banking with us.");
					System.exit(0);
				
			}
		}
			
			}	
			catch (IOException e) 
			{
				//e.printStackTrace();
				logger.error("Exception:" ,e);
			} catch (BankAccountNotFoundException e) {
				
				e.printStackTrace();
			}
		
			
	
	}
	
}	
