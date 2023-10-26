package com.crizen.task3.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.crizen.task3.service.ModifySlangService;

//QuartzJobBean은 복잡한 스케줄링에 적합한 유연한 스케줄러
//상속받아서 스케줄러 구현
public class ModifySlangJob extends QuartzJobBean {
	// 실제 실행될 테스크
    private ModifySlangService modifySlang;
    
    // 실제 실행될 테스크를 setter 방식 주입
    public void setModifySlang(ModifySlangService modifySlang) {
        this.modifySlang = modifySlang;
    }

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		modifySlang.modifySlang();
	}
    
}
