package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Reply;
import com.nesder.dao.entity.ReplyExample;
import com.nesder.dao.repository.ReplyMapper;
import com.nesder.vo.resq.AddReply;

@Service
@Transactional
public class ReplyService {

	@Autowired
	private ReplyMapper replymapper;

	/**
	 * 
	 * @return
	 */
	public List<Reply> findAll() {
		return replymapper.selectByExample(null);
	}
	
	/**
	 * 
	 * @param reply1
	 * @return
	 */
	public List<Reply> findAid(AddReply reply1) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andUidEqualTo(reply1.getUid());
		return replymapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param articleMark1
	 * @return
	 */
	public List<Reply> findUid(AddReply reply1) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andAidEqualTo(reply1.getAid());
		return replymapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param article1
	 * @return
	 */
	public int addReplyInfo(AddReply reply1) {
		// request data to DAO entity
		Reply reply = new Reply();
		reply.setContent(reply1.getContent());
		reply.setAid(reply1.getAid());
		reply.setUid(reply1.getUid());
		
		return replymapper.insertSelective(reply);
	}
	
	/**
	 * 
	 * @param aid
	 * @param uid
	 * @return
	 */
	public int deleteReply(Integer aid,Integer uid) {
		ReplyExample example = new ReplyExample();
		//条件
		example.createCriteria().andAidEqualTo(aid).andUidEqualTo(uid);
		return replymapper.deleteByExample(example);
	}
}
