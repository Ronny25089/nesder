package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Forum;
import com.nesder.dao.repository.ForumMapper;
import com.nesder.vo.resq.AddForum;

@Service
@Transactional
public class ForumService {

	@Autowired
	private ForumMapper moduleMapper;

	/**
	 * get all record of Forum
	 * @return
	 */
	public List<Forum> findAll() {
		return moduleMapper.selectByExample(null);
	}
	
	/**
	 * getById
	 * @param id
	 * @return
	 */
	public Forum getById(int id) {
		return moduleMapper.selectByPrimaryKey(id);
	}

	/**
	 * add a record Forum
	 * @param modle
	 * @return
	 */
	public int addForum(AddForum modle) {
		//request data to DAO entity
		Forum module = new Forum();
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		module.setCreated_account(modle.getCreated_account());
		
		return moduleMapper.insertSelective(module);
	}

	/**
	 * delete Forum by id
	 * @param id
	 * @return
	 */
	public int deleteForum(int id) {
		//条件
		return moduleMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * update Forum by id
	 * @param modle
	 * @return
	 */
	public int updateForum(AddForum modle) {
		//request data to DAO entity
		Forum module = new Forum();
		module.setForum_id(modle.getId());
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		module.setCreated_account(modle.getCreated_account());		
		
		return moduleMapper.updateByPrimaryKeySelective(module);
	}
}
