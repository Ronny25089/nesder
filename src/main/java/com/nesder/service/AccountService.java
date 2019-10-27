package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Account;
import com.nesder.dao.entity.AccountExample;
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

	/**
	 * get a record by id
	 * 
	 * @param id
	 * @return
	 */
	public Account findById(int id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	/**
	 * get all of account
	 * 
	 * @return
	 */
	public List<Account> findAll() {
		return accountMapper.selectByExample(null);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int sign(RegistUser user) {
		Account account = new Account();
		account.setAccount_id(user.getAccount_id());
		account.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		account.setBirthday(user.getBirthday());
		account.setEmail(user.getEmail());
		account.setGender(user.getGender());
		account.setAvatarurl(user.getAvatarurl());
		account.setNick_name(user.getNick_name());
		account.setIntroduction(user.getIntroduction());

		return accountMapper.insertSelective(account);
	}

	/**
	 * delete account by id
	 * 
	 * @param id
	 * @return
	 */
	public int delete(int id) {
		return accountMapper.deleteByPrimaryKey(id);
	}

	/**
	 * update Account by id
	 * 
	 * @param model
	 * @return
	 */
	public int updateAccount(RegistUser model) {
		// request data to DAO entity
		Account account = new Account();
		account.setId(model.getId());
		account.setBirthday(model.getBirthday());
		account.setEmail(model.getEmail());
		account.setNick_name(model.getNick_name());
		account.setIntroduction(model.getIntroduction());
		account.setAvatarurl(model.getAvatarurl());
		account.setRole(model.getRole());

		return accountMapper.updateByPrimaryKeySelective(account);
	}

	@Override
	public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
		AccountExample example = new AccountExample();
		example.createCriteria().andAccount_idEqualTo(accountId);
		List<Account> list = accountMapper.selectByExample(example);
		if (list.size() == 0) {

		}
		return new UserContext(list.get(0));
	}
}
