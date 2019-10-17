package com.nesder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.dao.domain.Account;
import com.nesder.dto.SignAccount;
import com.nesder.service.AccountService;

@RestController
@RequestMapping("/user")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<Account> listUser() {
        return accountService.findAll();
    }
	
	@RequestMapping(value="/sign",method=RequestMethod.POST)
	public String sign(@RequestBody SignAccount signAccount) {
		accountService.sign(signAccount);
		return "sigh";
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") int id) {
		accountService.delete(id);
        return "success";
    }
}
