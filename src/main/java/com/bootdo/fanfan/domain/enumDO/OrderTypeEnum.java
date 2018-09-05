package com.bootdo.fanfan.domain.enumDO;

public enum OrderTypeEnum {

    DiningRoom(1,"堂吃"),
    Pack(2,"打包"),
    TakeOut(3,"外卖");
    private Integer val;

    private String  text;

    OrderTypeEnum(Integer val,String text){
        this.text=text;
        this.val=val;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return val;
    }

    public static OrderTypeEnum get(Integer val){
       for (OrderTypeEnum orderTypeEnum:OrderTypeEnum.values()){
           if(orderTypeEnum.val==val){
               return orderTypeEnum;
           }
       }
        return OrderTypeEnum.DiningRoom;
    }
}
