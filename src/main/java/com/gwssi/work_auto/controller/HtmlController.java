package com.gwssi.work_auto.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/7/23 17:53
 */
@Slf4j
@Controller
public class HtmlController {
    @RequestMapping("/")
    public String sayHello() {
        log.info(new Date()+"访问主页");
        return "excel";
    }
}
