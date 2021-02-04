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
	private ForumMapper forumMapper;

	/**
	 * get all record of Forum
	 * @return
	 */
	public List<Forum> findAll() {
		return forumMapper.selectByExample(null);
	}
	
	/**
	 * getById
	 * @param id
	 * @return
	 */
	public Forum getById(int id) {
		return forumMapper.selectByPrimaryKey(id);
	}

	/**
	 * add a record Forum
	 * @param modle
	 * @return
	 */
	public int addForum(AddForum modle) {
		//request data to DAO entity
		Forum forum = new Forum();
		forum.setForum_name(modle.getmName());
		forum.setIntroduction(modle.getIntroduction());
		forum.setAccount_id(modle.getCreated_account());
		
		return forumMapper.insertSelective(forum);
	}

	/**
	 * delete Forum by id
	 * @param id
	 * @return
	 */
	public int deleteForum(int id) {
		//条件
		return forumMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * update Forum by id
	 * @param modle
	 * @return
	 */
	public int updateForum(AddForum modle) {
		//request data to DAO entity
		Forum forum = new Forum();
		forum.setForum_id(modle.getId());
		forum.setForum_name(modle.getmName());
		forum.setIntroduction(modle.getIntroduction());
		forum.setAccount_id(modle.getCreated_account());
		
		return forumMapper.updateByPrimaryKeySelective(forum);
	}
}
