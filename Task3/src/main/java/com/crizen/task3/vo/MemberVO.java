package com.crizen.task3.vo;

import java.util.*;

import org.springframework.security.core.*;

import lombok.*;

@Data
public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private Date regist_date;
	private Date update_date;
	private int fail_count;
	
	private int enabled;
	private int locked;
	private List<AuthVO> authList = new ArrayList<>();

}
