package com.nesder.service;

import com.nesder.dao.entity.*;
import com.nesder.dao.repository.*;
import com.nesder.model.DetailsModel;
import com.nesder.model.PostDetailsModel;
import com.nesder.utils.toolUtils;
import com.nesder.vo.resq.AddChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DetailsService {

	@Autowired
	private PostMapper articleMapper;

	@Autowired
	private PostMarkMapper articleMarkMapper;

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
		List<Post> postList = articleMapper.selectByExample(example);
		for (Post post : postList) {

			try {
				int postId = post.getPost_id();
				DetailsModel detailModel = new DetailsModel();

				// 取得收藏数
				PostMarkExample articleMarkExample1 = new PostMarkExample();
				articleMarkExample1.createCriteria().andPidEqualTo(postId);
				articleMarkExample1.createCriteria().andMarksEqualTo(true);
				// 收藏数
				int marksCount = articleMarkMapper.selectByExample(articleMarkExample1).size();

				// 取得点赞数
				PostMarkExample articleMarkExample2 = new PostMarkExample();
				articleMarkExample2.createCriteria().andPidEqualTo(postId);
				articleMarkExample2.createCriteria().andLikesEqualTo(true);
				// 收藏数
				int likesCount = articleMarkMapper.selectByExample(articleMarkExample2).size();

				// 取得评论数
				ReplyExample replyExample = new ReplyExample();
				replyExample.createCriteria().andPidEqualTo(postId);
				int replyCount = replyMapper.selectByExample(replyExample).size();

				// 取得作者信息
				AccountExample accountExample = new AccountExample();
				accountExample.createCriteria().andAccount_idEqualTo(post.getCreated_account());
				Account account = accountMapper.selectByExample(accountExample).get(0);

				detailModel.setPost_id(postId);
				detailModel.setChannel_id(post.getChannel_id());
				detailModel.setTitle(post.getTitle());
				detailModel.setContent(post.getContent());
				detailModel.setCreate_date(toolUtils.dateFormat(post.getCreate_date()));
				detailModel.setModify_date(toolUtils.dateFormat(post.getModify_date()));
				detailModel.setEnable_edit(post.getEnable_edit());
				detailModel.setCreated_account(post.getCreated_account());
				detailModel.setCreated_account_nick_name(account.getNick_name());
				detailModel.setCreated_account_gender(account.getGender());
				detailModel.setCreated_account_avatarurl(account.getAvatarurl());
				detailModel.setMarksCount(marksCount);
				detailModel.setLikesCount(likesCount);
				detailModel.setReplayCount(replyCount);

				result.add(detailModel);
			} catch (Exception e) {
				continue;
			}
		}
		return result;
	}

	public List<PostDetailsModel> findDetailsByPostId(Integer cid) {
		List<PostDetailsModel> result = new ArrayList<PostDetailsModel>();

		// 取得所有的内容
		PostExample example = new PostExample();

		return result;
	}
}
