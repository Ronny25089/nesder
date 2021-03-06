package com.nesder.dao.entity;

import java.util.Date;

public class Forum {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.forum.forum_id
     *
     * @mbg.generated
     */
    private Integer forum_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.forum.forum_name
     *
     * @mbg.generated
     */
    private String forum_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.forum.create_date
     *
     * @mbg.generated
     */
    private Date create_date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.forum.introduction
     *
     * @mbg.generated
     */
    private String introduction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.forum.account_id
     *
     * @mbg.generated
     */
    private Integer account_id;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.forum.forum_id
     *
     * @return the value of public.forum.forum_id
     *
     * @mbg.generated
     */
    public Integer getForum_id() {
        return forum_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.forum.forum_id
     *
     * @param forum_id the value for public.forum.forum_id
     *
     * @mbg.generated
     */
    public void setForum_id(Integer forum_id) {
        this.forum_id = forum_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.forum.forum_name
     *
     * @return the value of public.forum.forum_name
     *
     * @mbg.generated
     */
    public String getForum_name() {
        return forum_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.forum.forum_name
     *
     * @param forum_name the value for public.forum.forum_name
     *
     * @mbg.generated
     */
    public void setForum_name(String forum_name) {
        this.forum_name = forum_name == null ? null : forum_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.forum.create_date
     *
     * @return the value of public.forum.create_date
     *
     * @mbg.generated
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.forum.create_date
     *
     * @param create_date the value for public.forum.create_date
     *
     * @mbg.generated
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.forum.introduction
     *
     * @return the value of public.forum.introduction
     *
     * @mbg.generated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.forum.introduction
     *
     * @param introduction the value for public.forum.introduction
     *
     * @mbg.generated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.forum.account_id
     *
     * @return the value of public.forum.account_id
     *
     * @mbg.generated
     */
    public Integer getAccount_id() {
        return account_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.forum.account_id
     *
     * @param account_id the value for public.forum.account_id
     *
     * @mbg.generated
     */
    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }
}