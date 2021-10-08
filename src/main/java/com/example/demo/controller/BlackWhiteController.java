package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.callback.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author p78o2
 * @date 2021/9/17
 */
@RestController
@RequestMapping(value = "/black")
public class BlackWhiteController {
    @GetMapping(value = "/blackWhite")
    @SentinelResource(value = "blackWhite", blockHandler = "blackWhiteFailHandler")
    public R blackWhite(){
        return new R (true,0,null,"黑白名单正常返回");
    }
    public R blackWhiteFailHandler(BlockException blockException){
        return new R (false,0,null,"黑白名单异常返回");
    }

}
