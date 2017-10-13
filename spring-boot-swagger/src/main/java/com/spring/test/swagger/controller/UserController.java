package com.spring.test.swagger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.swagger.bean.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "测试swagger", tags = "测试swagger")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static Map<Integer, User> users = new ConcurrentHashMap<>();

    @ApiOperation(value = "获取所有user列表", notes = "获取所有user列表")
    @GetMapping("list")
    public List<User> list() {
        List<User> res = new ArrayList<>(users.values());
        return res;
    }

    @ApiOperation(value = "创建user", notes = "根据user对象创建用户")
    @ApiImplicitParam(name = "user", value = "user对象", required = true, dataType = "User")
    @PostMapping("create")
    public String create(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取user信息", notes = "根据id获取user信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
    @GetMapping("{id}")
    public User get(@PathVariable Integer id) {
        return users.get(id);
    }

    @ApiOperation(value = "更新user信息", notes = "根据id更新user")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path"),
                         @ApiImplicitParam(name = "user", value = "user对象", required = true, dataType = "User") })
    @PutMapping("update/{id}")
    public String update(@PathVariable Integer id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除user", notes = "根据id删除user")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        users.remove(id);
        return "success";
    }
}
