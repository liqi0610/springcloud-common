package cn.v5cn.springboot.cache.j2cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZYW
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public Object index(){
        return null;
    }

}
