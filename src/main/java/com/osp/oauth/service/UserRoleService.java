package com.osp.oauth.service;

import java.util.List;

import com.osp.oauth.model.SystemUser;
import com.osp.oauth.model.UserRole;

public interface UserRoleService {

	public List<UserRole> getRoleByUser(SystemUser user);
}
