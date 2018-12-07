package com.study.zhengql.innerClass;

/**
 * 描述:在Java中内部类主要分为成员内部类、局部内部类、匿名内部类、静态内部类。
 *
 * 成员内部类
 * @author zhengql
 * @date 2018/11/28 19:20
 */
public class OutClassTwo {

    private String str;

    private void output(){
        System.out.println(str);
    }

    public class InnerClass{
        InnerClass(){
            System.out.println("constructor.....");
        }
        public void innerMethod(){
            str = "aaa";
            output();
        }
    }

    private InnerClass getInnerClass(){
        return new InnerClass();
    }

    public static void main(String[] args) {
        OutClassTwo outClassTwo = new OutClassTwo();
        OutClassTwo.InnerClass innerClass = outClassTwo.getInnerClass();
        innerClass.innerMethod();
    }
}
