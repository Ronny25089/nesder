package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.FllowAccount;
import com.nesder.dao.entity.FllowAccountExample;
import com.nesder.dao.repository.FllowAccountMapper;
import com.nesder.vo.resq.AddFllowaccount;

@Service
@Transactional
public class FllowaccountService {

	@Autowired
	private FllowAccountMapper fllowAccountMapper;

	/**
	 * 
	 * @return
	 */
	public List<FllowAccount> findAll() {
		return fllowAccountMapper.selectByExample(null);
	}
	
	/**
	 * 
	 * @param fllowAccount1
	 * @return
	 */
	public List<FllowAccount> findFllowerId(AddFllowaccount fllowAccount1) {
		FllowAccountExample example = new FllowAccountExample();
		example.createCriteria().andFllowed_idEqualTo(fllowAccount1.getFllowed_id());
		return fllowAccountMapper.selectByExample(example);
	}
	/**
	 * 
	 * @param fllowAccount1
	 * @return
	 */
	public List<FllowAccount> findFllowedId(AddFllowaccount fllowAccount1) {
		FllowAccountExample example = new FllowAccountExample();
		example.createCriteria().andFllower_idEqualTo(fllowAccount1.getFllower_id());
		return fllowAccountMapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param fllowAccount1
	 * @return
	 */
	public int addfllowAccount(AddFllowaccount fllowAccount1) {
		// request data to DAO entity
		FllowAccount fllowAccount = new FllowAccount();
		fllowAccount.setFllowed_id(fllowAccount1.getFllowed_id());
		fllowAccount.setFllower_id(fllowAccount1.getFllower_id());

		return fllowAccountMapper.insertSelective(fllowAccount);
	}

	/**
	 * 
	 * @param fllowd_id 
	 * @param fllower_id 
	 * @return
	 */
	public int deletefllowAccount(Integer fllowd_id, Integer fllower_id) {
		FllowAccountExample example = new FllowAccountExample();
		example.createCriteria().andFllowed_idEqualTo(fllowd_id).andFllower_idEqualTo(fllower_id);
		// 条件
		return fllowAccountMapper.deleteByExample(example);
	}
}
