package com.bootdo.testDemo;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import lombok.Data;
import lombok.Getter;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: JY
 * @date: 2018/5/29 15:07
 */
public class Test {

    public static void main(String[] args) throws Throwable {

        System.out.println(1000+new Random().nextInt(999)+"");

        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(parent.class,"setName", MethodType.methodType(void.class,String.class));

        parent p = new parent();
        methodHandle.invoke(p,"名");

        System.out.println(p.getName());

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
