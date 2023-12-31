package com.crizen.task3.security;

import java.util.*;
import java.util.stream.*;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import com.crizen.task3.vo.*;

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	private MemberVO member;
	private boolean enabled;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO member) {
		super(member.getId(), member.getPasswd(), 
				member.getAuthList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList())
			);
		
		this.member = member;
		if(member.getLocked() == 0) {
			this.enabled = true;
		}else {
			this.enabled = false;
		}
	}
	
}

