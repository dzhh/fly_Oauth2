package com.osp.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.osp.oauth.model.SystemUser;
import com.osp.oauth.model.UserRole;
import com.osp.oauth.service.UserRoleService;

/**
 * 模拟用户的角色
 * @author fly
 *
 */
@Service("UserRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {

	public UserRoleServiceImpl() {
		UserRole role1 = new UserRole();
		role1.setId(new Long(1));
		role1.setRole("role1");
		
		UserRole role2 = new UserRole();
		role2.setId(new Long(2));
		role2.setRole("role2");
		
		List<UserRole> list1 = new ArrayList<UserRole>();
		List<UserRole> list2 = new ArrayList<UserRole>();
		list1.add(role1);
		list2.add(role1);
		list2.add(role2);
		userRoleMap.put("admin", list1);
		userRoleMap.put("fly", list2);
	}
	
	static Map<String, List<UserRole>> userRoleMap = new HashMap<String, List<UserRole>>();
	
	
	
	
	public List<UserRole> getRoleByUser(SystemUser user) {
		if(userRoleMap.containsKey(user.getUserName())) {
			return userRoleMap.get(user.getUserName());
		}
		
		return null;
	}

}
