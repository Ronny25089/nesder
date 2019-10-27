package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Article;
import com.nesder.dao.repository.ArticleMapper;
import com.nesder.vo.resq.AddArticle;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	/**
	 * 
	 * @return
	 */
	public List<Article> findAll() {
		return articleMapper.selectByExample(null);
	}
		
	/**
	 * 
	 * @param article1
	 * @return
	 */
	public int addAticleInfo(AddArticle article1) {
		// request data to DAO entity
		Article article = new Article();
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
	public int updateAticleInfo(AddArticle article1) {
		//条件
		Article article = new Article();
		article.setId(article1.getId());
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
	public int deleteArticle(int id) {
		return articleMapper.deleteByPrimaryKey(id);
	}
}
