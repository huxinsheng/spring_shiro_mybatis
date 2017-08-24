package com.hxs.ssm.utils.velocity;

import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.FormatConfig;

import java.util.Calendar;
import java.util.Date;

/**
 * @项目名称： hxs-mp
 * @类名称： DateWeekTool
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2016-12-18 11:14
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 * @Version v1.0
 */
@DefaultKey("dateWeek")
public class DateWeekTool extends FormatConfig {
    public Object format(Object content){
        if(content == null) return null;
        try{
            String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date)content);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
