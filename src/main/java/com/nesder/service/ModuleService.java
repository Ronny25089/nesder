package com.nesder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nesder.dao.domain.Module;
import com.nesder.dao.domain.ModuleExample;
import com.nesder.dao.repository.ModuleMapper;
import com.nesder.vo.resq.AddModule;

@Service
@Transactional
public class ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	public List<Module> findAll() {
		ModuleExample example = new ModuleExample();
		return moduleMapper.selectByExample(example);
	}

	public int addModule(AddModule modle) {
		Module module = new Module();
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		module.setCreated_account(modle.getCreatedAccount());
		
		return moduleMapper.insertSelective(module);
	}

	public int delete(int id) {
		ModuleExample example = new ModuleExample();
		example.createCriteria().andIdEqualTo(id);
		return moduleMapper.deleteByExample(example);
	}
	
	public int updateModule(AddModule modle) {
		ModuleExample example = new ModuleExample();
		example.createCriteria().andIdEqualTo(modle.getId()).andCreated_accountEqualTo(modle.getCreatedAccount());
		
		Module module = new Module();
		module.setMname(modle.getmName());
		module.setIntroduction(modle.getIntroduction());
		module.setCreated_account(modle.getCreatedAccount());
		
		return moduleMapper.updateByExampleSelective(module,example);
	}
}
