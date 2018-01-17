package com.spring.test.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.spring.test.starter.filter.CorsFilter;

@RestController
@SpringBootApplication
public class App extends WebMvcConfigurerAdapter {

    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/post")
    public String post(@RequestParam("json") JSONObject json) {
        return "Hello World!" + json;
    }

    @GetMapping("/poststr")
    public String postStr(@RequestParam("json") String json) {
        return "Hello World!" + json;
    }

    @PostMapping("/post0")
    public String post0(@RequestBody String hello,@RequestBody String world) {
        return "Hello World!" + hello + "---" + world;
    }

    @PostMapping("/post1")
    public String post0(@RequestBody String hello) {
        return "Hello World!" + hello;
    }
    
    

    @GetMapping("/get/{tenantId}/{storeId}")
    public String get0(@PathVariable String tenantId,@PathVariable String storeId) {
        return "Hello World!" + tenantId + "--" + storeId;
    }

    @PostMapping(value="/getPage")
    public String getPage(@RequestBody JSONObject param) {
        JSONObject json = new JSONObject();
        try {
            json.put("pageSize", 10);
            json.put("curPage", 1);
            json.put("totalCount", 35);
            json.put("pageCount", 4);
            JSONArray arr = new JSONArray();
            for(int i = 0; i < 35; i ++){
                JSONObject item = new JSONObject();
                item.put("age",i);
                item.put("name","name-" + i);
                arr.add(item);
            }
            json.put("data", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("allowOrigin", "*");
        registration.addInitParameter("allowHeaders", "*");
        registration.setName("CorsFilter");
        registration.setOrder(1);
        return registration;
    }
}