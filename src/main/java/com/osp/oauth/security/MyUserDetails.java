package com.osp.oauth.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.osp.oauth.model.SystemUser;
import com.osp.oauth.model.UserRole;

/**
 * UserDetails代表了Spring Security的用户认证实体，带有用户名、密码、权限列表、过期特性等性质，
 * 可以自己声明类实现UserDetails接口，如果不想自己声明，
 * 也可以用SpringSecurity的默认实现org.springframework.security.core.userdetails.User
 * @author fly
 *
 */
public class MyUserDetails extends SystemUser implements UserDetails{

    private List<UserRole> roles;

    public MyUserDetails(SystemUser user, List<UserRole> roles){
        super(user);
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null || roles.size() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(UserRole role : roles){
            commaBuilder.append(role.getRole()).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
