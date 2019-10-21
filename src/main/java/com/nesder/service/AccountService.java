package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.domain.Account;
import com.nesder.dao.domain.AccountExample;
import com.nesder.dao.repository.AccountMapper;
import com.nesder.model.UserContext;
import com.nesder.vo.resq.RegistUser;

@Service
@Transactional
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<Account> findAll() {
		AccountExample example = new AccountExample();
		return accountMapper.selectByExample(example);
	}

	public int sign(RegistUser user) {
		Account account = new Account();
		account.setAccount_id(user.getAccount_id());
		account.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		account.setAge(user.getAge());
		account.setEmail(user.getEmail());
		account.setGender(user.getGender());
		account.setAvatarurl(user.getAvatarurl());
		account.setNick_name(user.getNick_name());
		account.setIntroduction(user.getIntroduction());

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
		return new UserContext(list.get(0));
	}
}
