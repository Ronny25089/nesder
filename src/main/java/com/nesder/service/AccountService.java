package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.domain.Account;
import com.nesder.dao.domain.AccountExample;
import com.nesder.dao.repository.AccountMapper;
import com.nesder.dto.SignAccount;

@Service
@Transactional
public class AccountService {

	@Autowired(required = true)
	private AccountMapper accountMapper;

	public List<Account> findAll() {
		AccountExample example = new AccountExample();
		return accountMapper.selectByExample(example);
	}

	public int sign(SignAccount signAccount) {
		return accountMapper.insertSelective(signAccount);
	}

	public int delete(int Uid) {
		AccountExample example = new AccountExample();
		example.createCriteria().andUidEqualTo(Uid);
		return accountMapper.deleteByExample(example);
	}
}
