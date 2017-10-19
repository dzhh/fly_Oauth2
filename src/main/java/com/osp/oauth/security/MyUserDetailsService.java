package com.osp.oauth.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.osp.oauth.model.SystemUser;
import com.osp.oauth.model.UserRole;
import com.osp.oauth.service.SystemUserServiceImpl;
import com.osp.oauth.service.UserRoleServiceImpl;


@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService {
	
    
	@Resource(name = "SystemUserServiceImpl")
    private SystemUserServiceImpl systemUserService;

    @Resource(name = "UserRoleServiceImpl")
    private UserRoleServiceImpl userRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SystemUser user;
        try {
            user = systemUserService.findByName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if(user == null){
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                List<UserRole> roles = userRoleService.getRoleByUser(user);
                return new MyUserDetails(user, roles);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }

}
