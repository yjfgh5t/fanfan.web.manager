package com.bootdo.testDemo;

import com.bootdo.common.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/5/29 15:07
 */
public class Test {

    public static void main(String[] args) {

        Map<Integer,parent> test =new HashMap<>();

        parent dd = new parent();
        dd.setName("A");

        test.put(1,dd);

        dd = test.get(1);
        test.get(1).setName("B");


        System.out.println("结果集："+test.get(1).getName()+" 结果集2："+dd.getName());
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
