package com.osp.oauth.service;

import com.osp.oauth.model.SystemUser;

public interface SystemUserService {

	public SystemUser findByName(String userName);
}
