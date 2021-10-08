package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.callback.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author p78o2
 * @date 2021/8/13
 */
@RestController
@RequestMapping(value = "/flow")
public class FlowController {
    static int  i = 0 ;
//    限流模式：直接  限流效果：快速失败
    @GetMapping(value = "/directFail")
    @SentinelResource(value = "directFail", blockHandler = "directFailHandler")
    public R directFail(){
        return new R(true,0,"直接快速失败","success");
    }
    public R directFailHandler(BlockException blockException){
        i++;
        System.out.println(blockException.toString()+"   "+String.valueOf(i));
        return new R(false,-1,"直接快速失败","directFail");
    }
//    限流模式：关联  限流效果：快速失败
    @GetMapping(value = "/associationFail")
    @SentinelResource(value = "associationFail", blockHandler = "associationFailHandler")
    public R associationFail(){
        return new R(true,0,"关联快速失败","success");
    }
    public R associationFailHandler(BlockException blockException){
        System.out.println(blockException.toString());
        return new R(false,-1,"关联快速失败","associationFail");
    }
//    流控模式：直接  限流效果：排队等待
    @GetMapping(value = "/lineUpFail")
    @SentinelResource(value = "lineUpFail",blockHandler = "lineUpFailHandler")
    public R lineUpFail(){
        return new R(true,0,"直接排队失败","success");
    }
    public R lineUpFailHandler(BlockException blockException){
        i++;
        System.out.println(blockException.toString()+"   "+String.valueOf(i));
        return new R(false,-1,"直接排队失败","associationFail");
    }
}
