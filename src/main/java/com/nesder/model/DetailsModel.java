package com.nesder.model;

import java.util.Date;

public class DetailsModel {
    private Integer post_id;
    private Integer channel_id;
    private String title;
    private String content;
    private String create_date;
    private String modify_date;
    private Boolean enable_edit;
    private Integer created_account;
    private String created_account_nick_name;
    private Integer created_account_gender;
    private String created_account_avatarurl;
    private Integer marksCount;
    private Integer likesCount;
    private Integer replayCount;

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }

    public Boolean getEnable_edit() {
        return enable_edit;
    }

    public void setEnable_edit(Boolean enable_edit) {
        this.enable_edit = enable_edit;
    }

    public Integer getCreated_account() {
        return created_account;
    }

    public void setCreated_account(Integer created_account) {
        this.created_account = created_account;
    }

    public String getCreated_account_nick_name() {
        return created_account_nick_name;
    }

    public void setCreated_account_nick_name(String created_account_nick_name) {
        this.created_account_nick_name = created_account_nick_name;
    }

    public Integer getCreated_account_gender() {
        return created_account_gender;
    }

    public void setCreated_account_gender(Integer created_account_gender) {
        this.created_account_gender = created_account_gender;
    }

    public String getCreated_account_avatarurl() {
        return created_account_avatarurl;
    }

    public void setCreated_account_avatarurl(String created_account_avatarurl) {
        this.created_account_avatarurl = created_account_avatarurl;
    }

    public Integer getMarksCount() {
        return marksCount;
    }

    public void setMarksCount(Integer marksCount) {
        this.marksCount = marksCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(Integer replayCount) {
        this.replayCount = replayCount;
    }
}
