package com.erp.controller;

import org.springframework.stereotype.Controller;
import com.erp.utils.ShiroUtils;
import com.erp.domain.UserDO;
import com.erp.domain.UserToken;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}