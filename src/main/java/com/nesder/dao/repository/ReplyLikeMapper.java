package com.nesder.dao.repository;

import com.nesder.dao.entity.ReplyLike;
import com.nesder.dao.entity.ReplyLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyLikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    long countByExample(ReplyLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int deleteByExample(ReplyLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer reply_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int insert(ReplyLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int insertSelective(ReplyLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    List<ReplyLike> selectByExample(ReplyLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    ReplyLike selectByPrimaryKey(Integer reply_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ReplyLike record, @Param("example") ReplyLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ReplyLike record, @Param("example") ReplyLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReplyLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reply_like
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReplyLike record);
}