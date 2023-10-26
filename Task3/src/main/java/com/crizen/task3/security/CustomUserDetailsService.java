package com.crizen.task3.security;

import org.springframework.beans.factory.annotation.*;
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
		
		return member  == null ? null : new CustomUser(member);
	}

}
