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
	private PostMarkMapper postMarkMapper;

	/**
	 * 
	 * @return
	 */
	public List<PostMark> findAll() {
		return postMarkMapper.selectByExample(null);
	}
	
	/**
	 * 	
	 * @param postMark1
	 * @return
	 */
	public List<PostMark> findAid(AddPostMark postMark1) {
		PostMarkExample example = new PostMarkExample();
		example.createCriteria().andAccount_idEqualTo(postMark1.getUid());
		return postMarkMapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param postMark1
	 * @return
	 */
	public List<PostMark> findUid(AddPostMark postMark1) {
		PostMarkExample example = new PostMarkExample();
		example.createCriteria().andPost_idEqualTo(postMark1.getAid());
		return postMarkMapper.selectByExample(example);
	}
	
	
	/**
	 * 
	 * @param postMark1
	 * @return
	 */
	public int addAticleMarkInfo(AddPostMark postMark1) {
		// request data to DAO entity
		PostMark postMark = new PostMark();
		postMark.setPost_id(postMark1.getAid());
		postMark.setAccount_id(postMark1.getUid());
		
		return postMarkMapper.insertSelective(postMark);
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
		example.createCriteria().andPost_idEqualTo(aid).andAccount_idEqualTo(uid);
		return postMarkMapper.deleteByExample(example);
	}
}
