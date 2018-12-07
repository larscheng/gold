package com.study.zhengql.innerClass;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/11/28 19:09
 */
public class OutClassOne {
    private String name;
    private Integer age=10;

    public String getName() {
        return name;
    }

    public OutClassOne setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public OutClassOne setAge(Integer age) {
        this.age = age;
        return this;
    }




    public class InnerClass{
        public InnerClass(){
            name = "zhengql";
            age = 22;
        }
        public void output(){
            System.out.println(name+","+age);
        }

        public OutClassOne getOutClass(){
            return OutClassOne.this;
        }
    }

    public static void main(String[] args) {
        OutClassOne outClassOne = new OutClassOne();
        OutClassOne.InnerClass innerClass = outClassOne.new InnerClass();
        innerClass.output();
        System.out.println(innerClass.getOutClass().getAge());
    }
}
