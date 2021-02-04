package com.nesder.model;

import java.util.ArrayList;
import java.util.List;

public class PostReplyDetailsModel {
    private DetailsModel detailsModel = new DetailsModel();
    private List<ReplyDetailsModel> ReplyDetailsModelList = new ArrayList<ReplyDetailsModel>();

    public DetailsModel getDetailsModel() {
        return detailsModel;
    }

    public void setDetailsModel(DetailsModel detailsModel) {
        this.detailsModel = detailsModel;
    }

    public List<ReplyDetailsModel> getReplyDetailsModelList() {
        return ReplyDetailsModelList;
    }

    public void setReplyDetailsModelList(List<ReplyDetailsModel> replyDetailsModelList) {
        ReplyDetailsModelList = replyDetailsModelList;
    }
}
