package com.gwssi.work_auto.controller;

import com.alibaba.excel.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/7/23 16:54
 */
public class DateController {
    @Autowired
    private static RestTemplate restTemplate;
    public static int getIfHoliday(String date){
        int flag=1;
        try {
            Map<String, Object> map = restTemplate.getForObject("http://timor.tech/api/holiday/ "+ date, Map.class);
            if (map.get("holiday") == null) {
                flag = 1;
            } else {
                Map<String, Object> holidayDO = (Map) map.get("holiday");
                Boolean object = (Boolean) holidayDO.get("holiday");
                if (object) {
                    flag = 2;//节假日
                } else {
                    flag = 3;//节假日补班
                }
            }
            LocalDate localDate = LocalDate.parse(date);
            int value = localDate.getDayOfWeek().getValue();
            if(value==6 ||value==7){
                if(flag!=3){//如果不是节假日补班情况，则返回周末
                    flag=4;//周末
                }
            }
        }catch (Exception e){

        }
        return flag;
    }
    /**
     * 获取当月的所有工作日
     * @param year
     * @param month
     * @return
     */

    public static List<Date> getWolkdayInMonth(int year, int month) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        List<Date> list = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);// 不设置的话默认为当年
        calendar.set(Calendar.MONTH, month - 1);// 设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为当月第一天
        int daySize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最大天数
        for (int i = 0; i < daySize; i++) {
            String format = DateUtils.format(calendar.getTime(),"yyyy-MM-dd");
            int ifHoliday = getIfHoliday(format);
            if(ifHoliday!=2){
                int week = calendar.get(Calendar.DAY_OF_WEEK);
                //周末休息
                if ((week == Calendar.SATURDAY || week == Calendar.SUNDAY)) {// 1代表周日，7代表周六 判断这是一个星期的第几天从而判断是否是周末
                    //如果是补班，则属于正常上班时间
                    if(ifHoliday==3){
                        list.add(strToDate(year+"/"+month+"/"+calendar.get(Calendar.DAY_OF_MONTH)));
                    }
                }else{
                    // 得到当天是一个月的第几天
                    list.add(strToDate(year+"/"+month+"/"+calendar.get(Calendar.DAY_OF_MONTH)));
                }

            }
            calendar.add(Calendar.DATE, 1);//在第一天的基础上加1

        }
        return list;
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
}

