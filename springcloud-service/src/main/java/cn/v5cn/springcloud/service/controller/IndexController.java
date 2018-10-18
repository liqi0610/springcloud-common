package cn.v5cn.springcloud.service.controller;

import cn.v5cn.springcloud.authres.annotation.PreAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @PreAuth("hasAuthority('user_list')")
    @GetMapping("/index")
    public String index() {
        return "Hello index!";
    }

    @GetMapping("/index2")
    public String index2() {
        return "Hello index2!";
    }

    @PreAuth("hasAuthority('user_view')")
    @GetMapping("/index3")
    public String index3() {
        return "Hello index3!";
    }
}
