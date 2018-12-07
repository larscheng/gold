package com.study.zhengql;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/11/1 15:13
 */

@RestController
public class BaseController {
    @RestController
    public class HelloSpringBoot {
        @RequestMapping(path = {"/helloSpringBoot"})
        public String HelloSpring (){
            System.out.println("hello spring boot");
            return "hello spring boot";
        }
    }
}
