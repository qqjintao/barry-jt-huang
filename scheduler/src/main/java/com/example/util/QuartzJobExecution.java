package com.example.util;

import com.example.model.SysJob;
import org.quartz.JobExecutionContext;

/**
 * @author barry.jt.huang
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
