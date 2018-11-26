package cn.v5cn.springcloud.nacos.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyanwei
 */
@RestController
@RefreshScope
public class IndexController {

    @Value("${gasmeter.path}")
    private String path;

    @Value("${gasmeter.key}")
    private String key;

    @GetMapping("/nacos/config")
    public String index(){
        return "nacos: " + path + "######" + key;
    }
}
