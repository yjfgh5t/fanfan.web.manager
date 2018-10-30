package com.bootdo.testDemo;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/5/29 15:07
 */
public class Test {

    public static void main(String[] args) {

        BigDecimal zero = new BigDecimal(0),two = new BigDecimal(2.03);

        System.out.println(two.doubleValue());

        zero.add(two);

        System.out.println(zero.toBigInteger().intValue());
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
