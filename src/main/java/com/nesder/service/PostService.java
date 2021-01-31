package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Post;
import com.nesder.dao.repository.PostMapper;
import com.nesder.vo.resq.AddPost;

@Service
@Transactional
public class PostService {

	@Autowired
	private PostMapper articleMapper;

	/**
	 * 
	 * @return
	 */
	public List<Post> findAll() {
		return articleMapper.selectByExample(null);
	}
		
	/**
	 * 
	 * @param article1
	 * @return
	 */
	public int addAticleInfo(AddPost article1) {
		// request data to DAO entity
		Post article = new Post();
		article.setTitle(article1.getTitle());
		article.setContent(article1.getContent());
		article.setChannel_id(article1.getChannel_id());
		article.setCreated_account(article1.getCreated_account());
		
		return articleMapper.insertSelective(article);
	}
	
	/**
	 * 
	 * @param article1
	 * @return
	 */
	public int updateAticleInfo(AddPost article1) {
		//条件
		Post article = new Post();
		article.setPost_id(article1.getId());
		article.setTitle(article1.getTitle());
		article.setContent(article1.getContent());
		article.setChannel_id(article1.getChannel_id());
		article.setCreated_account(article1.getCreated_account());

		return articleMapper.updateByPrimaryKeySelective(article);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deletePost(int id) {
		return articleMapper.deleteByPrimaryKey(id);
	}
}
