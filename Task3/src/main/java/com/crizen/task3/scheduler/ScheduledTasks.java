package com.crizen.task3.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crizen.task3.service.ModifySlangServiceImpl;
import com.crizen.task3.service.RemoveOvertimeServiceImpl;

@Component
public class ScheduledTasks {
	
	@Autowired
	private RemoveOvertimeServiceImpl removeOvertimeService;

	@Autowired
	private ModifySlangServiceImpl modifySlangService;
	
	@Scheduled(cron = "0 0/1 * * * ?")
    public void performRemoveOverTimeJob() {
        removeOvertimeService.removeOvertime();
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void performModifySlangJob() {
    	modifySlangService.modifySlang();
   }
}
