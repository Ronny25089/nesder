package com.nesder.service;

import java.util.List;

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
	 * @param channel1
	 * @return
	 */
	public int addChannel(AddChannel channel1) {
		// request data to DAO entity
		Channel channel = new Channel();
		channel.setName(channel1.getName());
		channel.setMid(channel1.getMid());
		channel.setIntroduction(channel1.getIntroduction());
		channel.setCreated_account(channel1.getCreated_account());

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
		channel.setId(channel1.getId());
		channel.setName(channel1.getName());
		channel.setIntroduction(channel1.getIntroduction());
		channel.setCreated_account(channel1.getCreated_account());

		return channelMapper.updateByPrimaryKeySelective(channel);
	}
}
