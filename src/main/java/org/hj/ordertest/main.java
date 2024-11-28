package org.hj.ordertest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class main {

    @GetMapping("/")
    public @ResponseBody String index(){
        return "test 화면입니다.";
    }
}
