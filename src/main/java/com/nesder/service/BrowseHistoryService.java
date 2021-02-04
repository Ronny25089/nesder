package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.BrowseHistory;
import com.nesder.dao.entity.BrowseHistoryExample;
import com.nesder.dao.repository.BrowseHistoryMapper;
import com.nesder.vo.resq.AddBrowseHistory;

@Service
@Transactional
public class BrowseHistoryService {

	@Autowired
	private BrowseHistoryMapper browsehistorymapper;

	/**
	 * 
	 * @return
	 */
	public List<BrowseHistory> findAll() {
		return browsehistorymapper.selectByExample(null);
	}
	
	/**
	 * 
	 * @param browsehistory1
	 * @return
	 */
	public List<BrowseHistory> findPid(AddBrowseHistory browsehistory1) {
		BrowseHistoryExample example = new BrowseHistoryExample();
		example.createCriteria().andAccount_idEqualTo(browsehistory1.getUid());
		return browsehistorymapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param browsehistory1
	 * @return
	 */
	public List<BrowseHistory> findUid(AddBrowseHistory browsehistory1) {
		BrowseHistoryExample example = new BrowseHistoryExample();
		example.createCriteria().andPost_idEqualTo(browsehistory1.getPid());
		return browsehistorymapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param browsehistory1
	 * @return
	 */
	public int addBrowseHistoryInfo(AddBrowseHistory browsehistory1) {
		// request data to DAO entity
		BrowseHistory browseHistory = new BrowseHistory();
		browseHistory.setPost_id(browsehistory1.getPid());
		browseHistory.setAccount_id(browsehistory1.getUid());
		
		return browsehistorymapper.insertSelective(browseHistory);
	}
	
	/**
	 * 
	 * @param aid
	 * @param uid
	 * @return
	 */
	public int deleteReply(Integer aid,Integer uid) {
		BrowseHistoryExample example = new BrowseHistoryExample();
		//条件
		example.createCriteria().andPost_idEqualTo(aid).andAccount_idEqualTo(uid);
		return browsehistorymapper.deleteByExample(example);
	}
}
