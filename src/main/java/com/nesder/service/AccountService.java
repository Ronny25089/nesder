package com.nesder.service;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.nesder.utils.PropertiesUtil;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.RegistUser;

import io.netty.util.internal.StringUtil;

@Service
@Transactional
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountMapper accountMapper;
	
	
	@Autowired	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    //属性文件的路径 
    static String profilepath="message.properties"; 

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
	  * アカウント申込処理
	  * @param applicationUserInfo
	  * @return
	  */
	public ApiResponse applications(RegistUser applicationUserInfo) {
		ApiResponse apiResponse = new ApiResponse();
		if(this.userInfoCheck(applicationUserInfo)) {
			if(this.insertAccount(applicationUserInfo)<0) {
				apiResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
				apiResponse.setData("1");
				apiResponse.setMsg(PropertiesUtil.getEnvConfig("dbFailed"));

			}else {
				apiResponse.setStatusCode(CONSTANT.INPUT_ERR_CODE);
				apiResponse.setMsg(PropertiesUtil.getEnvConfig("dbFailed"));
			}
		}
		return apiResponse;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean userInfoCheck(RegistUser user) {
		try{
			if(!(null==user)) {
				this.checkNickName(user.getNick_name());
				this.checkEmailFormat(user.getEmail());
				this.checkpassword(user.getPassword());
			}
		}catch (Exception e){
			System.out.println("処理失敗しました。");
		}
		return true;
	}
	
	/**
	 * ユーザ名チェック
	 */
	private boolean checkNickName(String nickName) {
		AccountExample accountExample=new AccountExample();
		if(nickName != null && nickName.length() != 0){
			accountExample.createCriteria().andNick_nameLike(nickName);
			List<Account> accountList=accountMapper.selectByExample(accountExample);
			if(accountList.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * メールアドレスのフォーマットチェック
	 */
	private boolean checkEmailFormat(String email) {
		AccountExample accountExample=new AccountExample();
		if(!StringUtil.isNullOrEmpty(email)){
			accountExample.createCriteria().andEmailEqualTo(email);
			List<Account> accountList=accountMapper.selectByExample(accountExample);
			if(accountList.isEmpty()) {
				return true;
			}else {
				String REGEX="^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
			    Pattern p = Pattern.compile(REGEX);    
			    Matcher matcher=p.matcher(email);    
			    return matcher.matches();
			}
		}
		return false;
	}
	/**
	 * パスワードチェック
	 */
	private boolean checkpassword(String password) {
		if(!StringUtil.isNullOrEmpty(password)){
			return true;
		}
		return false;
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
	 *  insert Account
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
