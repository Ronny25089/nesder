package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Module;
import com.nesder.dao.entity.ModuleExample;
import com.nesder.dao.repository.ModuleMapper;
import com.nesder.vo.resq.AddModule;

@Service
@Transactional
public class ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	/**
	 * get all record of Module
	 * @return
	 */
	public List<Module> findAll() {
		ModuleExample example = new ModuleExample();
		return moduleMapper.selectByExample(example);
	}

	/**
	 * add a record Module
	 * @param modle
	 * @return
	 */
	public int addModule(AddModule modle) {
		//request data to DAO entity
		Module module = new Module();
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		module.setCreated_account(modle.getCreatedAccount());
		
		return moduleMapper.insertSelective(module);
	}

	/**
	 * delete Module by id
	 * @param id
	 * @return
	 */
	public int deleteModule(int id) {
		//条件
		ModuleExample example = new ModuleExample();
		example.createCriteria().andIdEqualTo(id);
		return moduleMapper.deleteByExample(example);
	}
	
	/**
	 * update Module by id
	 * @param modle
	 * @return
	 */
	public int updateModule(AddModule modle) {
		//条件
		ModuleExample example = new ModuleExample();
		example.createCriteria().andIdEqualTo(modle.getId());
		//request data to DAO entity
		Module module = new Module();
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		
		return moduleMapper.updateByExampleSelective(module,example);
	}
}
