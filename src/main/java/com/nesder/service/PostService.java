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
	private PostMapper postMapper;

	/**
	 * 
	 * @return
	 */
	public List<Post> findAll() {
		return postMapper.selectByExample(null);
	}
		
	/**
	 * 
	 * @param post1
	 * @return
	 */
	public int addAticleInfo(AddPost post1) {
		// request data to DAO entity
		Post post = new Post();
		post.setTitle(post1.getTitle());
		post.setContent(post1.getContent());
		post.setChannel_id(post1.getChannel_id());
		post.setAccount_id(post1.getCreated_account());
		
		return postMapper.insertSelective(post);
	}
	
	/**
	 * 
	 * @param post1
	 * @return
	 */
	public int updateAticleInfo(AddPost post1) {
		//条件
		Post post = new Post();
		post.setPost_id(post1.getId());
		post.setTitle(post1.getTitle());
		post.setContent(post1.getContent());
		post.setChannel_id(post1.getChannel_id());
		post.setAccount_id(post1.getCreated_account());

		return postMapper.updateByPrimaryKeySelective(post);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deletePost(int id) {
		return postMapper.deleteByPrimaryKey(id);
	}
}
