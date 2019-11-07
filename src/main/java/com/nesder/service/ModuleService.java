package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.entity.Module;
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
		return moduleMapper.selectByExample(null);
	}
	
	/**
	 * getById
	 * @param id
	 * @return
	 */
	public Module getById(int id) {
		return moduleMapper.selectByPrimaryKey(id);
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
		module.setCreated_account(modle.getCreated_account());
		
		return moduleMapper.insertSelective(module);
	}

	/**
	 * delete Module by id
	 * @param id
	 * @return
	 */
	public int deleteModule(int id) {
		//条件
		return moduleMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * update Module by id
	 * @param modle
	 * @return
	 */
	public int updateModule(AddModule modle) {
		//request data to DAO entity
		Module module = new Module();
		module.setId(modle.getId());
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		module.setCreated_account(modle.getCreated_account());		
		
		return moduleMapper.updateByPrimaryKeySelective(module);
	}
}
