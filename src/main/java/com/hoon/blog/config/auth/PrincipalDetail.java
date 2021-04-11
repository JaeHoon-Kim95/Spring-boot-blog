package com.hoon.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hoon.blog.model.User;

import lombok.Data;
import lombok.Getter;

@Getter
// 시큐리티 세션에 UserDetails정보만 저장 가능
public class PrincipalDetail implements UserDetails {
	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	//계정이 만료되지 않았는지 리턴(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {	
		return true;
	}

	//계정이 잠기지 않았는지 리턴(true:잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {	
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴(true:만료되지 않음)
	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}
	
	//계정이 활성화 되어있는지 리턴(true:활성화)
	@Override
	public boolean isEnabled() {		
		return true;
	}
	
	//계정이 갖고있는 권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
}
