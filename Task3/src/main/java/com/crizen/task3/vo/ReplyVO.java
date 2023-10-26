package com.crizen.task3.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyVO {
	private int seq_reply;
	private int seq_counsel;
	private String reply_writer;
	private String reply_content;
	private Timestamp reply_date;
}
