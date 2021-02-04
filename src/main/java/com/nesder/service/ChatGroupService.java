package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.ChatGroup;
import com.nesder.dao.repository.ChatGroupMapper;
import com.nesder.vo.resq.AddChatGroup;

@Service
@Transactional
public class ChatGroupService {

	@Autowired
	private ChatGroupMapper chatgroupMapper;

	/**
	 * get all record of Forum
	 * @return
	 */
	public List<ChatGroup> findAll() {
		return chatgroupMapper.selectByExample(null);
	}

	/**
	 * 
	 * @param chatGroup1
	 * @return
	 */
	public int addChatGroup(AddChatGroup chatGroup1) {
		//request data to DAO entity
		ChatGroup chatGroup = new ChatGroup();
		chatGroup.setChat_group_name(chatGroup1.getgName());
		chatGroup.setIntroduction(chatGroup1.getIntroduction());
		chatGroup.setAvatorurl(chatGroup1.getAvatorUrl());
		chatGroup.setCreate_account_id(chatGroup1.getCreated_account());
		chatGroup.setChat_type(chatGroup1.getChat_Type());
		
		return chatgroupMapper.insertSelective(chatGroup);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteChatGroup(int id) {
		//条件
		return chatgroupMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @param chatGroup1
	 * @return
	 */
	public int updateChatGroup(AddChatGroup chatGroup1) {
		//request data to DAO entity
		ChatGroup chatGroup = new ChatGroup();
		chatGroup.setChat_group_id(chatGroup1.getId());
		chatGroup.setChat_group_name(chatGroup1.getgName());
		chatGroup.setIntroduction(chatGroup1.getIntroduction());
		chatGroup.setCreate_account_id(chatGroup1.getCreated_account());
		chatGroup.setAvatorurl(chatGroup1.getAvatorUrl());
		chatGroup.setChat_type(chatGroup1.getChat_Type());	
		
		return chatgroupMapper.updateByPrimaryKey(chatGroup);
	}
}
