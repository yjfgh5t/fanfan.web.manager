package com.bootdo.fanfan.manager.model.xg;

import lombok.Getter;

/**
 * @author: JY
 * @date: 2018/11/19 11:16
 */
public class XGAcceptTimeModel {

    @Getter
    private Time start;

    @Getter
    private Time end;

    public void setStart(String hour,String min){
        this.start = new Time(hour,min);
    }

    public void setEnd(String hour,String min){
        this.end = new Time(hour,min);
    }

    class Time{
        /**
         * 小时
         */
        private String hour;

        /**
         * 分钟
         */
        private String min;

        public  Time(){

        }

        public  Time(String hour,String min){
            this.hour = hour;
            this.min = min;
        }
    }
}
