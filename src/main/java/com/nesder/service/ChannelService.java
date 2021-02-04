package com.nesder.service;

import java.util.List;

import com.nesder.dao.entity.ChannelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Channel;
import com.nesder.dao.repository.ChannelMapper;
import com.nesder.vo.resq.AddChannel;

@Service
@Transactional
public class ChannelService {

	@Autowired
	private ChannelMapper channelMapper;

	/**
	 * 
	 * @return
	 */
	public List<Channel> findAll() {
		return channelMapper.selectByExample(null);
	}

	/**
	 *
	 * @param mid
	 * @return
	 */
	public List<Channel> findByForumId(Integer mid) {
		ChannelExample example = new ChannelExample();
		example.createCriteria().andFidEqualTo(mid);
		return channelMapper.selectByExample(example);
	}

	/**
	 * 
	 * @param channel1
	 * @return
	 */
	public int addChannel(AddChannel channel1) {
		// request data to DAO entity
		Channel channel = new Channel();
		channel.setChannel_name(channel1.getName());
		channel.setFid(channel1.getFid());
		channel.setIntroduction(channel1.getIntroduction());
		channel.setAccount_id(channel1.getCreated_account());

		return channelMapper.insertSelective(channel);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteChannel(int id) {
		// 条件
		return channelMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 * @param channel1
	 * @return
	 */
	public int updateChannel(AddChannel channel1) {
		// 条件
		Channel channel = new Channel();
		channel.setChannel_id(channel1.getId());
		channel.setChannel_name(channel1.getName());
		channel.setIntroduction(channel1.getIntroduction());
		channel.setAccount_id(channel1.getCreated_account());

		return channelMapper.updateByPrimaryKeySelective(channel);
	}
}
