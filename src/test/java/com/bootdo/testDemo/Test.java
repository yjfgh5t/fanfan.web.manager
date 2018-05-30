package com.bootdo.testDemo;

import com.bootdo.common.utils.MD5Utils;

/**
 * @author: JY
 * @date: 2018/5/29 15:07
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(MD5Utils.encrypt("admin", "111111"));

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
