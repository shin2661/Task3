package com.crizen.task3.vo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class CounselVO {
	private int seq_counsel;
	private String counsel_writer;
	private String counsel_subject;
	private String counsel_content;
	private Timestamp counsel_date;
	private String counsel_update_writer;
	private Timestamp counsel_update_date;
}
