package org.cap.service;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.exception.InvalidInitialAmountException;
import org.cap.util.AccountUtil;

public class AccountServiceImpl implements AcccountService{
	
	private AccountDao accountDao;
	
	
	public AccountServiceImpl(){}

	public AccountServiceImpl(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}

	public Account addAccount(Customer customer, double amount) throws InvalidInitialAmountException {
		
		if(customer==null)
			throw new IllegalArgumentException();
		if(amount<500)
			throw new InvalidInitialAmountException();
		
		Account account=new Account();
		account.setAccountNo(AccountUtil.generateAccountNumber());
		account.setCustomer(customer);
		account.setAmount(amount);
		
		if(accountDao.createAccount(account))
			return account;
		
		return null;
	}

	public Account findAccountById(int accountNo) {
		
		return accountDao.findAccountById(accountNo);
	}

	public Account withdraw(int accountNo, double amount) throws InsufficientBalanceException {
		
		Account account=accountDao.findAccountById(accountNo);
		
		if(account.getAmount()<amount)
			throw new InsufficientBalanceException();
		
		account.setAmount(account.getAmount()-amount);
		
		
		return account;
	}

	public Account deposit(int accountNo, double amount) {
		Account account=accountDao.findAccountById(accountNo);
		
		account.setAmount(account.getAmount()+amount);
		
		return account;
	}
	
	
	
	public int addNumbers(int num1,int num2){
		return num1+num2;
	}
	
	public void myLoop(){
		long sum=0;
		for(int i=0;i<100000000;i++)
			sum+=i;
	}
	
	
	
	
	
	

}
