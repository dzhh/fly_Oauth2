package com.osp.oauth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.osp.oauth.model.SystemUser;

/**
 * 模拟获取用户
 * @author fly
 *
 */
@Service("SystemUserServiceImpl")
public class SystemUserServiceImpl {

	public SystemUserServiceImpl() {
		SystemUser user1 = new SystemUser();
		user1.setId(new Long(1));
		user1.setUserName("admin");
		user1.setPassword("admin");
		userMap.put("admin", user1);
		
		SystemUser user2 = new SystemUser();
		user2.setId(new Long(2));
		user2.setUserName("fly");
		user2.setPassword("fly");
		userMap.put("fly", user2);
	}
	
	static Map<String, SystemUser> userMap = new HashMap<String, SystemUser>();
	
	
	public SystemUser findByName(String userName) {
		if(userMap.containsKey(userName)) {
			return userMap.get(userName);
		}
		
		return null;
	}

}
