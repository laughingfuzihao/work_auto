package com.gwssi.work_auto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/7/23 17:53
 */
@Controller
public class HtmlController {
    @RequestMapping("/")
    public String sayHello() {
        return "excel";
    }
}
