package org.xianghao.xmall.promotion.service.impl;

import org.xianghao.xmall.api.constant.promotion.PromotionActivityStatus;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.promotion.dao.PromotionActivityDAO;
import org.xianghao.xmall.promotion.domain.PromotionActivityDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 促销活动状态检查任务
 * @author xianghao
 *
 */
@Component
public class PromotionActivityStatusCheckTask {
	
	private static final Logger logger = LoggerFactory.getLogger(
			PromotionActivityStatusCheckTask.class);

	/**
	 * 促销活动管理DAO组件
	 */
	@Autowired
	private PromotionActivityDAO promotionActivityDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 执行业务逻辑
	 */
    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void execute() {
    	try {
    		List<PromotionActivityDO> activities = promotionActivityDAO.listAll();
        	
        	for(PromotionActivityDO activity : activities) {
        		if(PromotionActivityStatus.DISABLED.equals(activity.getStatus())) {
        			tryEnableActivity(activity); 
        		} else if(PromotionActivityStatus.ENABLED.equals(activity.getStatus())) {
        			tryDisableActivity(activity); 
        		}
        	}
		} catch (Exception e) {
			logger.error("error", e); 
		}
    }
    
    /**
     * 尝试启用促销活动
     * @param activity 促销活动
     * @throws Exception
     */
    private void tryEnableActivity(PromotionActivityDO activity) throws Exception {
    	Date currentTime = dateProvider.getCurrentTime();
    	if(currentTime.after(activity.getStartTime())) {  
    		activity.setStatus(PromotionActivityStatus.ENABLED);
    		activity.setGmtModified(dateProvider.getCurrentTime());  
    		promotionActivityDAO.updateStatus(activity);  
    	}
    }
    
    /**
     * 尝试禁用促销活动
     * @param activity 促销活动
     * @throws Exception
     */
    private void tryDisableActivity(PromotionActivityDO activity) throws Exception {
    	Date currentTime = dateProvider.getCurrentTime();
    	if(currentTime.after(activity.getEndTime())) {   
    		activity.setStatus(PromotionActivityStatus.DISABLED);
    		activity.setGmtModified(dateProvider.getCurrentTime()); 
    		promotionActivityDAO.updateStatus(activity);  
    	}
    }

}