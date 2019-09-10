package cn.v5cn.springboot.security.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZYW
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "hello index;";
    }

    @GetMapping("/json")
    public Object json(){
        return JSON.parse("[\n" +
                "    {\n" +
                "        \"ID\": \"5d452e6b-71e0-67ae-5169-c7e6342da53b\",\n" +
                "        \"Node\": \"Jacks-MacBook-Air.local\",\n" +
                "        \"Address\": \"127.0.0.1\",\n" +
                "        \"Datacenter\": \"dc1\",\n" +
                "        \"TaggedAddresses\": {\n" +
                "            \"lan\": \"127.0.0.1\",\n" +
                "            \"wan\": \"127.0.0.1\"\n" +
                "        },\n" +
                "        \"NodeMeta\": {\n" +
                "            \"consul-network-segment\": \"\"\n" +
                "        },\n" +
                "        \"ServiceID\": \"csi\",\n" +
                "        \"ServiceName\": \"moguhu_server\",\n" +
                "        \"ServiceTags\": [\n" +
                "            \"dev\"\n" +
                "        ],\n" +
                "        \"ServiceAddress\": \"127.0.0.1\",\n" +
                "        \"ServiceMeta\": {},\n" +
                "        \"ServicePort\": 8081,\n" +
                "        \"ServiceEnableTagOverride\": false,\n" +
                "        \"CreateIndex\": 10,\n" +
                "        \"ModifyIndex\": 10\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"5d452e6b-71e0-67ae-5169-c7e6342da53b\",\n" +
                "        \"Node\": \"Jacks-MacBook-Air.local\",\n" +
                "        \"Address\": \"127.0.0.1\",\n" +
                "        \"Datacenter\": \"dc1\",\n" +
                "        \"TaggedAddresses\": {\n" +
                "            \"lan\": \"127.0.0.1\",\n" +
                "            \"wan\": \"127.0.0.1\"\n" +
                "        },\n" +
                "        \"NodeMeta\": {\n" +
                "            \"consul-network-segment\": \"\"\n" +
                "        },\n" +
                "        \"ServiceID\": \"oc\",\n" +
                "        \"ServiceName\": \"moguhu_server\",\n" +
                "        \"ServiceTags\": [\n" +
                "            \"dev\"\n" +
                "        ],\n" +
                "        \"ServiceAddress\": \"127.0.0.1\",\n" +
                "        \"ServiceMeta\": {},\n" +
                "        \"ServicePort\": 8082,\n" +
                "        \"ServiceEnableTagOverride\": false,\n" +
                "        \"CreateIndex\": 12,\n" +
                "        \"ModifyIndex\": 12\n" +
                "    }\n" +
                "]");
    }

}
