package com.crizen.task3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfoVO {
	private int listCount; // 총 게시물 수
	private int pageListLimit; // 페이지 당 표시할 페이지 번호 갯수 (pageSize)
	private int maxPage; // 전체 페이지 수(최대 페이지 번호)
	private int startPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호
}
