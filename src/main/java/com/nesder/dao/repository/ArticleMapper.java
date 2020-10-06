package com.nesder.dao.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.nesder.dao.entity.Article;
import com.nesder.dao.entity.ArticleExample;

public interface ArticleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	long countByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int deleteByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer article_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int insert(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int insertSelective(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	List<Article> selectByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	Article selectByPrimaryKey(Integer article_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.article
	 * @mbg.generated
	 */
	int updateByPrimaryKey(Article record);
}