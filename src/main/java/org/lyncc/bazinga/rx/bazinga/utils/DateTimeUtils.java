package org.lyncc.bazinga.rx.bazinga.utils;

import com.alibaba.fastjson.JSON;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liguolin
 * @create 2018-06-15 14:11
 **/
public class DateTimeUtils {


    /**
     * 获取到当前凌晨时间
     * @return
     */
    public static String getCurrentDayWeeTime(){
        DateTime dt=new DateTime().withMillisOfDay(0);
        return dt.toString("yyyy-MM-dd");
    }

    public static void main(String[] args) {
        Map<String,Object> maps = new HashMap<String,Object>();
        maps.put("mobiles", Arrays.asList("15195817212"));
        maps.put("signName", "艾佳生活");
        maps.put("smsKey", "new_device_offline");

        Map<String,Object> cmaps = new HashMap<String,Object>();
        cmaps.put("customer","BazingaLyncc");
        maps.put("smsParams", JSON.toJSONString(cmaps));

        System.out.println(JSON.toJSONString(maps));
    }


    public static String getExactTime(){
        return null;
    }
}
