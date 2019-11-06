package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Reply2Reply;
import com.nesder.dao.entity.Reply2ReplyExample;
import com.nesder.dao.repository.Reply2ReplyMapper;
import com.nesder.vo.resq.AddReply2Reply;

@Service
@Transactional
public class Reply2ReplyService {

	@Autowired
	private Reply2ReplyMapper reply2replymapper;

	/**
	 * 
	 * @return
	 */
	public List<Reply2Reply> findAll() {
		return reply2replymapper.selectByExample(null);
	}
	
	/**
	 * 
	 * @param reply2reply1
	 * @return
	 */
	public List<Reply2Reply> findAid(AddReply2Reply reply2reply1) {
		Reply2ReplyExample example = new Reply2ReplyExample();
		example.createCriteria().andUidEqualTo(reply2reply1.getUid());
		return reply2replymapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param reply2reply1
	 * @return
	 */
	public List<Reply2Reply> findUid(AddReply2Reply reply2reply1) {
		Reply2ReplyExample example = new Reply2ReplyExample();
		example.createCriteria().andAidEqualTo(reply2reply1.getAid());
		return reply2replymapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param reply2reply1
	 * @return
	 */
	public List<Reply2Reply> findRid(AddReply2Reply reply2reply1) {
		Reply2ReplyExample example = new Reply2ReplyExample();
		example.createCriteria().andAidEqualTo(reply2reply1.getAid());
		example.createCriteria().andUidEqualTo(reply2reply1.getUid());
		return reply2replymapper.selectByExample(example);
	}
	
	
	/**
	 * 
	 * @param reply2reply1
	 * @return
	 */
	public int addReply2ReplyInfo(AddReply2Reply reply2reply1) {
		// request data to DAO entity
		Reply2Reply reply2Reply = new Reply2Reply();
		reply2Reply.setContent(reply2reply1.getContent());
		reply2Reply.setAid(reply2reply1.getAid());
		reply2Reply.setUid(reply2reply1.getUid());
		reply2Reply.setRid(reply2reply1.getRid());
		
		return reply2replymapper.insertSelective(reply2Reply);
	}
	
	/**
	 * 
	 * @param aid
	 * @param uid
	 * @param rid
	 * @return
	 */
	public int deleteReply2Reply(Integer aid,Integer uid,Integer rid) {
		Reply2ReplyExample example = new Reply2ReplyExample();
		//条件
		example.createCriteria().andAidEqualTo(aid).andUidEqualTo(uid).andRidEqualTo(rid);
		return reply2replymapper.deleteByExample(example);
	}
}
