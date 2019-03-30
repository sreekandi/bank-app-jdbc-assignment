package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.util.DbUtil;

public class BankAccountDaoImpl implements BankAccountDao {

	@Override
	public double getBalance(long accountId) {

		String query = "select account_balance from bankaccount where account_id=" + accountId;

		double balance = -1;
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {
			if (result.next())
				balance = result.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public void updateBalance(long accountId, double newBalance) {

		String query = "update bankaccount set account_balance=? where account_id=? ";
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);

			int result = statement.executeUpdate();
			System.out.println("No of rows is updated:" + result);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		String query = "delete from bankaccount where account_id=" + accountId;
		int result;
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			result = statement.executeUpdate();

			if (result == 1)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean addNewAccount(BankAccount account) {
		String query = "insert into bankaccount (customer_name, account_type, account_balance) values(?, ?, ?)";
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, account.getAccountHolderName());
			statement.setString(2, account.getAccountType());
			statement.setDouble(3, account.getAccountBalance());
			int result = statement.executeUpdate();
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		String query = "select * from bankaccount";
		List<BankAccount> accounts = new ArrayList<>();
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				long accountId = result.getLong(1);
				String accountHolderName = result.getString(2);
				String accountType = result.getString(3);
				double accountBalance = result.getDouble(4);
				BankAccount account = new BankAccount(accountId, accountHolderName, accountType, accountBalance);
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public BankAccount searchBankAccount(long accountId) {
		String query = " select * from bankAccount where account_id=" + accountId;
		BankAccount accountdetails = null;
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {

			if (result.next())
				;
			accountdetails = new BankAccount(result.getLong(1), result.getString(2), result.getString(3),
					result.getDouble(4));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountdetails;

	}

	@Override
	public boolean updateBankAccount(String accountHolderName, String accountType) {
		String query = "update bankaccount set customer_name = ? ,account_typr =? where account_id=?";
		Connection connection = DbUtil.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, accountHolderName);
			statement.setString(2, accountType);

			int result = statement.executeUpdate();
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	

}
