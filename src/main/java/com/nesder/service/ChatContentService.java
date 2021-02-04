package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.ChatContent;
import com.nesder.dao.repository.ChatContentMapper;
import com.nesder.vo.resq.AddChatContent;

@Service
@Transactional
public class ChatContentService {

	@Autowired
	private ChatContentMapper chatcontentMapper;

	/**
	 * 
	 * @return
	 */
	public List<ChatContent> findAll() {
		return chatcontentMapper.selectByExample(null);
	}

	/**
	 * 
	 * @param chatcontent1
	 * @return
	 */
	public int addChatContent(AddChatContent chatcontent1) {
		// request data to DAO entityØ
		ChatContent chatContent = new ChatContent();
		chatContent.setContent(chatcontent1.getContent());
		chatContent.setChat_group_id(chatcontent1.getChat_Group_Id());
		chatContent.setAccount_id(chatcontent1.getCreated_account());
		
		return chatcontentMapper.insertSelective(chatContent);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteChatContent(int id) {
		// 条件
		return chatcontentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 * @param chatcontent1
	 * @return
	 */
	public int updateChatContent(AddChatContent chatcontent1) {
		// request data to DAO entity
		ChatContent chatContent = new ChatContent();
		chatContent.setChat_content_id(chatcontent1.getId());
		chatContent.setContent(chatcontent1.getContent());
		chatContent.setChat_group_id(chatcontent1.getChat_Group_Id());
		chatContent.setAccount_id(chatcontent1.getCreated_account());

		return chatcontentMapper.updateByPrimaryKeySelective(chatContent);
	}
}
