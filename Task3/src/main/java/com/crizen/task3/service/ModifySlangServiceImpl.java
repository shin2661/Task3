package com.crizen.task3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crizen.task3.mapper.ModifySlangMapper;
import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.ReplyVO;
import com.crizen.task3.vo.SlangVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModifySlangServiceImpl implements ModifySlangService {

	@Autowired
	private ModifySlangMapper mapper;
	
	@Override
	public void modifySlang() {
		
		List<SlangVO> slangList = mapper.selectSlangList();
		List<CounselVO> counselList = mapper.selectCounselList();
		List<ReplyVO> replyList = mapper.selectReplyList();
		
		for(CounselVO counsel : counselList) {
			for(SlangVO slang : slangList) {
				
				boolean isUpdated = false;
				
				if(counsel.getCounsel_writer().contains(slang.getSlang())){
					String masked = counsel.getCounsel_writer().replaceAll(slang.getSlang(), "*****");
					counsel.setCounsel_writer(masked);
					isUpdated = true;
				}

				if(counsel.getCounsel_subject().contains(slang.getSlang())){
					String masked = counsel.getCounsel_subject().replaceAll(slang.getSlang(), "*****");
					counsel.setCounsel_subject(masked);
					isUpdated = true;
				}

				if(counsel.getCounsel_content().contains(slang.getSlang())){
					String masked = counsel.getCounsel_content().replaceAll(slang.getSlang(), "*****");
					counsel.setCounsel_content(masked);
					isUpdated = true;
				}
				
				if(isUpdated) {
					mapper.updateCounselSlang(counsel);
				}
			}
		}
		log.info("=============== 게시글 비속어 처리 ===============");
		
		for(ReplyVO reply : replyList) {
			for(SlangVO slang : slangList) {
				boolean isUpdated = false;
				
				if(reply.getReply_writer().contains(slang.getSlang())){
					String masked = reply.getReply_writer().replaceAll(slang.getSlang(), "*****");
					reply.setReply_writer(masked);
					isUpdated = true;
				}
	
				if(reply.getReply_content().contains(slang.getSlang())){
					String masked = reply.getReply_content().replaceAll(slang.getSlang(), "*****");
					reply.setReply_content(masked);
					isUpdated = true;
				}
	
				if(isUpdated) {
					mapper.updateReplySlang(reply);
				}
			}
		}
		log.info("================ 댓글 비속어 처리 ================");
		
	}

}
