package com.nesder.service;

import com.nesder.dao.entity.*;
import com.nesder.dao.repository.*;
import com.nesder.model.DetailsModel;
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
	private ArticleMapper articleMapper;

	@Autowired
	private ArticleMarkMapper articleMarkMapper;

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
		ArticleExample example = new ArticleExample();
		if (cid != 1001) {
			example.createCriteria().andChannel_idEqualTo(cid);
		}
		List<Article> postList = articleMapper.selectByExample(example);
		for (Article post : postList) {

			try {
				int postId = post.getArticle_id();
				DetailsModel detailModel = new DetailsModel();

				// 取得收藏数
				ArticleMarkExample articleMarkExample1 = new ArticleMarkExample();
				articleMarkExample1.createCriteria().andAidEqualTo(postId);
				articleMarkExample1.createCriteria().andMarksEqualTo(true);
				// 收藏数
				int marksCount = articleMarkMapper.selectByExample(articleMarkExample1).size();

				// 取得点赞数
				ArticleMarkExample articleMarkExample2 = new ArticleMarkExample();
				articleMarkExample2.createCriteria().andAidEqualTo(postId);
				articleMarkExample2.createCriteria().andLikesEqualTo(true);
				// 收藏数
				int likesCount = articleMarkMapper.selectByExample(articleMarkExample2).size();

				// 取得评论数
				ReplyExample replyExample = new ReplyExample();
				replyExample.createCriteria().andAidEqualTo(postId);
				int replyCount = replyMapper.selectByExample(replyExample).size();

				// 取得作者信息
				AccountExample accountExample = new AccountExample();
				accountExample.createCriteria().andAccount_idEqualTo(post.getCreated_account());
				Account account = accountMapper.selectByExample(accountExample).get(0);

				detailModel.setArticle_id(postId);
				detailModel.setChannel_id(post.getChannel_id());
				detailModel.setTitle(post.getTitle());
				detailModel.setContent(post.getContent());
				detailModel.setCreate_date(post.getCreate_date());
				detailModel.setModify_date(post.getModify_date());
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
}
