package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Article;
import com.nesder.dao.entity.ArticleMark;
import com.nesder.dao.entity.ArticleMarkExample;
import com.nesder.dao.repository.ArticleMarkMapper;
import com.nesder.vo.resq.AddArticle;
import com.nesder.vo.resq.AddArticleMark;

@Service
@Transactional
public class ArticleMarkService {

	@Autowired
	private ArticleMarkMapper articleMarkMapper;

	/**
	 * 
	 * @return
	 */
	public List<ArticleMark> findAll() {
		return articleMarkMapper.selectByExample(null);
	}
		
	/**
	 * 
	 * @param articleMark1
	 * @return
	 */
	public int addAticleMarkInfo(AddArticleMark articleMark1) {
		// request data to DAO entity
		ArticleMark articleMark = new ArticleMark();
		articleMark.setAid(articleMark1.getAid());
		articleMark.setUid(articleMark1.getUid());
		
		return articleMarkMapper.insertSelective(articleMark);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteArticleMark(Integer aid,Integer uid) {
		ArticleMarkExample example = new ArticleMarkExample();
		//条件
		example.createCriteria().andAidEqualTo(aid).andUidEqualTo(uid);
		return articleMarkMapper.deleteByExample(example);
	}
}
