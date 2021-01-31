package com.nesder.service;

import java.util.ArrayList;
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
import com.nesder.utils.CONSTANT;
import com.nesder.utils.PropertiesUtils;
import com.nesder.vo.resp.ApiResponse;
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
	 * 用户注册
	 * 
	 * @param applicationUserInfo
	 * @return
	 */
	public ApiResponse applications(RegistUser applicationUserInfo) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<String> errMsgs = this.userInfoCheck(applicationUserInfo);
			if (errMsgs.size() == 0) {
				if (this.insertAccount(applicationUserInfo) > 0) {
					apiResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
					apiResponse.setData("1");
					apiResponse.setMsg(PropertiesUtils.getEnvConfig("ERR.002"));

				}
			} else {
				apiResponse.setStatusCode(CONSTANT.INPUT_ERR_CODE);
				apiResponse.setData(errMsgs);
				apiResponse.setMsg(PropertiesUtils.getEnvConfig("ERR.003"));

			}
		} catch (Exception e) {
			apiResponse.setStatusCode(CONSTANT.INPUT_ERR_CODE);
			apiResponse.setMsg(PropertiesUtils.getEnvConfig("ERR.000"));
		}
		return apiResponse;
	}

	/**
	 * 用户注册check
	 * @param user
	 * @return
	 */
	public List<String> userInfoCheck(RegistUser user) {
		List<String> msgList = new ArrayList<String>();
		AccountExample accountExample = new AccountExample();
		// 用户名check
		if (user.getNick_name().isEmpty()) {
			msgList.add(PropertiesUtils.getEnvConfig("ERR.004"));
		}
		accountExample.createCriteria().andNick_nameEqualTo(user.getNick_name());
		List<Account> accountList1 = accountMapper.selectByExample(accountExample);
		if (!accountList1.isEmpty()) {
			msgList.add(PropertiesUtils.getEnvConfig("ERR.008"));
		}
		// 邮箱check
		if (user.getEmail().isEmpty()) {
			msgList.add(PropertiesUtils.getEnvConfig("ERR.005"));
		}
		accountExample.createCriteria().andEmailEqualTo(user.getEmail());
		List<Account> accountList2 = accountMapper.selectByExample(accountExample);
		if (!accountList2.isEmpty()) {
			msgList.add(PropertiesUtils.getEnvConfig("ERR.007"));
		}
		// 密码check
		if (user.getPassword().isEmpty()) {
			msgList.add(PropertiesUtils.getEnvConfig("ERR.006"));
		}
		return msgList;
	}
	
	/**
	 * 登陆处理
	 * @param user
	 * @return
	 */
	public ApiResponse signAccount(RegistUser user) {
		ApiResponse apiResponse = new ApiResponse();
		AccountExample accountExample = new AccountExample();
		List<String> msgList = new ArrayList<String>();
		accountExample.createCriteria().andEmailEqualTo(user.getEmail()).andPasswordEqualTo(user.getPassword());
		List<Account> accountList = accountMapper.selectByExample(accountExample);
		if (!accountList.isEmpty()) {
			msgList.add(user.getEmail());
			msgList.add(user.getPassword());
			apiResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
			apiResponse.setData(msgList);
		} else {
			apiResponse.setStatusCode(CONSTANT.INPUT_ERR_CODE);
			apiResponse.setMsg(PropertiesUtils.getEnvConfig("ERR.009"));
		}

		return apiResponse;
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
	 * insert Account
	 * 
	 * @param model
	 * @return
	 */
	public int insertAccount(RegistUser model) {
		// request data to DAO entity
		Account account = new Account();
		account.setBirthday(model.getBirthday());
		account.setEmail(model.getEmail());
		account.setNick_name(model.getNick_name());
		account.setIntroduction(model.getIntroduction());
		account.setAvatarurl(model.getAvatarurl());
		account.setRole(model.getRole());
		account.setPassword(model.getPassword());
		account.setGender(model.getGender());

		return accountMapper.insertSelective(account);
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
		account.setAccount_id(model.getId());
		account.setBirthday(model.getBirthday());
		account.setEmail(model.getEmail());
		account.setNick_name(model.getNick_name());
		account.setIntroduction(model.getIntroduction());
		account.setAvatarurl(model.getAvatarurl());
		account.setRole(model.getRole());
		account.setPassword(model.getPassword());
		account.setGender(model.getGender());

		return accountMapper.insertSelective(account);
	}

	@Override
	public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
		AccountExample example = new AccountExample();
		example.createCriteria().andAccount_idEqualTo(Integer.valueOf(accountId));
		List<Account> list = accountMapper.selectByExample(example);
		if (list.size() == 0) {

		}
		return new UserContext(list.get(0));
	}
}
