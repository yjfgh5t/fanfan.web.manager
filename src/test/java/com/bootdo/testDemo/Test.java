package com.bootdo.testDemo;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/5/29 15:07
 */
public class Test {

    public static void main(String[] args) {

        String ff = "{\"orderTime\":\"2018-10-22 21:31:46\",\"orderDeskNum\":\"\",\"orderRemark\":\"\",\"orderNum\":\"15402150872460\",\"orderDateNum\":\"2\",\"details\":[{\"outPrice\":12,\"outTitle\":\"小鸟一只\",\"outSize\":1,\"outType\":1},{\"outPrice\":8,\"outTitle\":\"咖啡一杯-小份\",\"outSize\":1,\"outType\":5}],\"orderPay\":20,\"orderTotal\":20}";

        APIOrderRequVO model =  JSON.parseObject(ff, APIOrderRequVO.class);

        System.out.println(model);
    }


    private static void say(parent _parent){

        child _child = (child)_parent;

        System.out.println(_child.getAge());
        System.out.println(_child.getName());

    }


    static class parent{

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }

    static class child extends  parent{
        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        private Integer age;
    }

}
