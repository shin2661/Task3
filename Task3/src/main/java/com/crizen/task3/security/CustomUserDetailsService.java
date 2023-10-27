package com.crizen.task3.security;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import com.crizen.task3.mapper.*;
import com.crizen.task3.vo.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Setter@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.warn("Load User By userId : "+userId);
		
		MemberVO member = mapper.read(userId);
		
//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		if(member == null) {
//			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
		}
		
		return new CustomUser(member);
//		return member  == null ? null : new CustomUser(member);
	}

}
