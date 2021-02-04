package com.nesder.service;

import com.nesder.dao.entity.*;
import com.nesder.dao.repository.*;
import com.nesder.model.DetailsModel;
import com.nesder.model.PostReplyDetailsModel;
import com.nesder.model.ReplyDetailsModel;
import com.nesder.utils.toolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DetailsService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private PostMarkMapper postMarkMapper;

	@Autowired
	private ReplyMapper replyMapper;

	@Autowired
	private AccountMapper accountMapper;

	/**
     * 通过频道ID的所有发布的内容
	 * @param cid
     * @return
     */
	public List<DetailsModel> findByChannelId(Integer cid) {
		List<DetailsModel> result = new ArrayList<DetailsModel>();

		// 取得所有的内容
		PostExample example = new PostExample();
		if (cid != 1001) {
			example.createCriteria().andChannel_idEqualTo(cid);
		}
		List<Post> postList = postMapper.selectByExample(example);
		for (Post post : postList) {

			try {
				result.add(this.getPostDetailsByPost(post));
			} catch (Exception e) {
				continue;
			}
		}
		return result;
	}

	/**
	 * 通过文章id获得所有评论信息
	 * @param postId
	 * @return
	 */
	public PostReplyDetailsModel findDetailsByPostId(Integer postId) throws Exception {
		PostReplyDetailsModel result = new PostReplyDetailsModel();

		// 取得文章
		Post post = postMapper.selectByPrimaryKey(postId);
		result.setDetailsModel(this.getPostDetailsByPost(post));

		// 取得评论
		ReplyExample replyExample = new ReplyExample();
		replyExample.createCriteria().andPost_idEqualTo(postId);
		List<Reply> replyList = replyMapper.selectByExample(replyExample);

		List<ReplyDetailsModel> replyDetailsModelList = new ArrayList<ReplyDetailsModel>();
		for (Reply reply : replyList) {
			try {
				ReplyDetailsModel replyDetailsModel = new ReplyDetailsModel();
				Account replyAccount = accountMapper.selectByPrimaryKey(reply.getAccount_id());

				//回复数
				ReplyExample replyExample2 = new ReplyExample();
				replyExample2.createCriteria().andReply_to_reply_idEqualTo(reply.getReply_id());
				int replyCounts = replyMapper.selectByExample(replyExample2).size();

				//点赞数
				ReplyLikeExample replyLikeExample = new ReplyLikeExample();
				replyLikeExample.createCriteria().andReply_idEqualTo(reply.getReply_id());


				replyDetailsModel.setReply_id(reply.getReply_id());
				replyDetailsModel.setContent(reply.getContent());
				replyDetailsModel.setCreate_date(toolUtils.dateFormat(reply.getCreate_date()));
				replyDetailsModel.setModify_date(toolUtils.dateFormat(reply.getModify_date()));
				replyDetailsModel.setEnable_edit(reply.getEnable_edit());
				replyDetailsModel.setCreated_account(replyAccount.getAccount_id());
				replyDetailsModel.setCreated_account_nick_name(replyAccount.getNick_name());
				replyDetailsModel.setCreated_account_gender(replyAccount.getGender());
				replyDetailsModel.setCreated_account_avatarurl(replyAccount.getAvatarurl());
				replyDetailsModel.setLikesCount(replyCounts);
				replyDetailsModel.setReplayCount(replyCounts);

				replyDetailsModelList.add(replyDetailsModel);
			} catch (Exception e) {
				continue;
			}
		}

		result.setReplyDetailsModelList(replyDetailsModelList);

		return result;
	}

	/**
	 * 取得文章详细
	 * @param post
	 * @return
	 * @throws Exception
	 */
	private DetailsModel getPostDetailsByPost(Post post) throws Exception{
		int postId = post.getPost_id();
		DetailsModel detailModel = new DetailsModel();

		// 取得收藏数
		PostMarkExample postMarkExample1 = new PostMarkExample();
		postMarkExample1.createCriteria().andPost_idEqualTo(postId);
		postMarkExample1.createCriteria().andMarksEqualTo(true);
		// 收藏数
		int marksCount = postMarkMapper.selectByExample(postMarkExample1).size();

		// 取得点赞数
		PostMarkExample postMarkExample2 = new PostMarkExample();
		postMarkExample2.createCriteria().andPost_idEqualTo(postId);
		postMarkExample2.createCriteria().andLikesEqualTo(true);
		// 收藏数
		int likesCount = postMarkMapper.selectByExample(postMarkExample2).size();

		// 取得评论数
		ReplyExample replyExample = new ReplyExample();
		replyExample.createCriteria().andPost_idEqualTo(postId);
		int replyCount = replyMapper.selectByExample(replyExample).size();

		// 取得作者信息
		Account account = accountMapper.selectByPrimaryKey(post.getAccount_id());

		detailModel.setPost_id(postId);
		detailModel.setChannel_id(post.getChannel_id());
		detailModel.setTitle(post.getTitle());
		detailModel.setContent(post.getContent());
		detailModel.setCreate_date(toolUtils.dateFormat(post.getCreate_date()));
		detailModel.setModify_date(toolUtils.dateFormat(post.getModify_date()));
		detailModel.setEnable_edit(post.getEnable_edit());
		detailModel.setCreated_account(post.getAccount_id());
		detailModel.setCreated_account_nick_name(account.getNick_name());
		detailModel.setCreated_account_gender(account.getGender());
		detailModel.setCreated_account_avatarurl(account.getAvatarurl());
		detailModel.setMarksCount(marksCount);
		detailModel.setLikesCount(likesCount);
		detailModel.setReplayCount(replyCount);

		return detailModel;
	}
}
