package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.domain.Account;
import com.nesder.dao.domain.AccountExample;
import com.nesder.dao.repository.AccountMapper;
import com.nesder.model.JwtUser;

@Service
@Transactional
public class AccountService implements UserDetailsService {

	@Autowired(required = true)
	private AccountMapper accountMapper;

	public List<Account> findAll() {
		AccountExample example = new AccountExample();
		return accountMapper.selectByExample(example);
	}

	public int sign(Account account) {
		return accountMapper.insertSelective(account);
	}

	public int delete(int id) {
		AccountExample example = new AccountExample();
		example.createCriteria().andIdEqualTo(id);
		return accountMapper.deleteByExample(example);
	}

	@Override
	public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
		AccountExample example = new AccountExample();
		example.createCriteria().andAccount_idEqualTo(accountId);
		List<Account> list = accountMapper.selectByExample(example);
		return new JwtUser(list.get(0).getAccount_id(), list.get(0).getPassword());
	}
}
