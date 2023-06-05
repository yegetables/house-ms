package com.xupt.house.mapper;

/**
 * Author:关昌隆
 * Date:2022/9/23 15:08
 **/
class Test {
    String name;
    int age;
    Test(){
        name = "haha";
        age = 20;
    }

    void test(){
        System.out.println(name+"666");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.name = "2333";
        test.age = 30;
        test.test();
        System.out.println(test.name);
        Test test1 = new Test();
    }
}
