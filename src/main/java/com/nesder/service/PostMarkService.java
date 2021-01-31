package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.PostMark;
import com.nesder.dao.entity.PostMarkExample;
import com.nesder.dao.repository.PostMarkMapper;
import com.nesder.vo.resq.AddPostMark;

@Service
@Transactional
public class PostMarkService {

	@Autowired
	private PostMarkMapper articleMarkMapper;

	/**
	 * 
	 * @return
	 */
	public List<PostMark> findAll() {
		return articleMarkMapper.selectByExample(null);
	}
	
	/**
	 * 	
	 * @param articleMark1
	 * @return
	 */
	public List<PostMark> findAid(AddPostMark articleMark1) {
		PostMarkExample example = new PostMarkExample();
		example.createCriteria().andUidEqualTo(articleMark1.getUid());
		return articleMarkMapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param articleMark1
	 * @return
	 */
	public List<PostMark> findUid(AddPostMark articleMark1) {
		PostMarkExample example = new PostMarkExample();
		example.createCriteria().andPidEqualTo(articleMark1.getAid());
		return articleMarkMapper.selectByExample(example);
	}
	
	
	/**
	 * 
	 * @param articleMark1
	 * @return
	 */
	public int addAticleMarkInfo(AddPostMark articleMark1) {
		// request data to DAO entity
		PostMark articleMark = new PostMark();
		articleMark.setPid(articleMark1.getAid());
		articleMark.setUid(articleMark1.getUid());
		
		return articleMarkMapper.insertSelective(articleMark);
	}

	/**
	 *
	 * @param aid
	 * @param uid
	 * @return
	 */
	public int deletePostMark(Integer aid,Integer uid) {
		PostMarkExample example = new PostMarkExample();
		//条件
		example.createCriteria().andPidEqualTo(aid).andUidEqualTo(uid);
		return articleMarkMapper.deleteByExample(example);
	}
}
