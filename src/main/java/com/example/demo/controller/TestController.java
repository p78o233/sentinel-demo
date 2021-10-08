package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author p78o2
 * @date 2021/8/6
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/index")
    @SentinelResource(value = "helloworld", blockHandler = "blockHandler", fallback = "fallback")
//    使用SentinelResource不支持private方法
    public void index(@RequestParam int test) {
        // 资源中的逻辑
        int i = 3 / 0;
        System.out.println("hellotest" + test);
    }

    @GetMapping(value = "/random")
    @SentinelResource(value = "random", blockHandler = "sleep")
    public void random() throws InterruptedException {
        double rank = Math.random()*100;
        if(rank>=50){
            Thread.sleep(500);
        }else{
            Thread.sleep(300);
        }

    }
    public void sleep( BlockException blockException) {
        System.out.println(blockException.toString());
    }

    //    这个是限流之后的配置
    public void blockHandler(int test, BlockException blockException) {
        System.out.println(blockException.toString());
       if(blockException.toString().equals("com.alibaba.csp.sentinel.slots.block.degrade.DegradeException")){
           System.out.println("服务被降级");
       }
    }

    public void fallback() {
        System.out.println("fallBack");
    }
}
