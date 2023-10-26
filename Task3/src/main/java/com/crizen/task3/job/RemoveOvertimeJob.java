package com.crizen.task3.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.crizen.task3.service.RemoveOvertimeService;

public class RemoveOvertimeJob extends QuartzJobBean {
	// 실제 실행될 테스크
    private RemoveOvertimeService removeOvertime;
    
    // 실제 실행될 테스크를 setter 방식 주입
    public void setRemoveOvertime(RemoveOvertimeService removeOvertime) {
        this.removeOvertime = removeOvertime;
    }
    
    

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		this.removeOvertime.removeOvertime();
	}
    
    
}
