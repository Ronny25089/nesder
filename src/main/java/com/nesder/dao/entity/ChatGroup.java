package com.nesder.dao.entity;

import java.util.Date;

public class ChatGroup {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.chat_group_id
     *
     * @mbg.generated
     */
    private Integer chat_group_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.account_id
     *
     * @mbg.generated
     */
    private Integer account_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.chat_group_name
     *
     * @mbg.generated
     */
    private String chat_group_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.introduction
     *
     * @mbg.generated
     */
    private String introduction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.avatorurl
     *
     * @mbg.generated
     */
    private String avatorurl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.chat_type
     *
     * @mbg.generated
     */
    private Integer chat_type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.create_account_id
     *
     * @mbg.generated
     */
    private Integer create_account_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.chat_group.create_date
     *
     * @mbg.generated
     */
    private Date create_date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.chat_group_id
     *
     * @return the value of public.chat_group.chat_group_id
     *
     * @mbg.generated
     */
    public Integer getChat_group_id() {
        return chat_group_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.chat_group_id
     *
     * @param chat_group_id the value for public.chat_group.chat_group_id
     *
     * @mbg.generated
     */
    public void setChat_group_id(Integer chat_group_id) {
        this.chat_group_id = chat_group_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.account_id
     *
     * @return the value of public.chat_group.account_id
     *
     * @mbg.generated
     */
    public Integer getAccount_id() {
        return account_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.account_id
     *
     * @param account_id the value for public.chat_group.account_id
     *
     * @mbg.generated
     */
    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.chat_group_name
     *
     * @return the value of public.chat_group.chat_group_name
     *
     * @mbg.generated
     */
    public String getChat_group_name() {
        return chat_group_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.chat_group_name
     *
     * @param chat_group_name the value for public.chat_group.chat_group_name
     *
     * @mbg.generated
     */
    public void setChat_group_name(String chat_group_name) {
        this.chat_group_name = chat_group_name == null ? null : chat_group_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.introduction
     *
     * @return the value of public.chat_group.introduction
     *
     * @mbg.generated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.introduction
     *
     * @param introduction the value for public.chat_group.introduction
     *
     * @mbg.generated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.avatorurl
     *
     * @return the value of public.chat_group.avatorurl
     *
     * @mbg.generated
     */
    public String getAvatorurl() {
        return avatorurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.avatorurl
     *
     * @param avatorurl the value for public.chat_group.avatorurl
     *
     * @mbg.generated
     */
    public void setAvatorurl(String avatorurl) {
        this.avatorurl = avatorurl == null ? null : avatorurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.chat_type
     *
     * @return the value of public.chat_group.chat_type
     *
     * @mbg.generated
     */
    public Integer getChat_type() {
        return chat_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.chat_type
     *
     * @param chat_type the value for public.chat_group.chat_type
     *
     * @mbg.generated
     */
    public void setChat_type(Integer chat_type) {
        this.chat_type = chat_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.create_account_id
     *
     * @return the value of public.chat_group.create_account_id
     *
     * @mbg.generated
     */
    public Integer getCreate_account_id() {
        return create_account_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.create_account_id
     *
     * @param create_account_id the value for public.chat_group.create_account_id
     *
     * @mbg.generated
     */
    public void setCreate_account_id(Integer create_account_id) {
        this.create_account_id = create_account_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.chat_group.create_date
     *
     * @return the value of public.chat_group.create_date
     *
     * @mbg.generated
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.chat_group.create_date
     *
     * @param create_date the value for public.chat_group.create_date
     *
     * @mbg.generated
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}