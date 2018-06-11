package com.jm.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jm.server.model.Test;
import com.jm.server.service.ITestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@RestController
@Api(description = "活动管理")
public class UserController {
    @Autowired
    private ITestService testServiceImpl;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    public Map login(){
        List<String> list = new ArrayList();
        list.add("萌萌萌");
        list.add("萌萌萌");
        list.add("萌萌萌");
        list.add("萌萌萌");
        list.add("郑佳蒙");
        list.add("撒打算");
        System.out.println("");
        Map map = new HashMap();
        for (String s : list) {
            if(!map.containsKey(s)){
                map.put(s,s);
            }
        }
        return map;
    }
    @RequestMapping(value = "findAll",method = RequestMethod.POST)
    @ApiOperation(value="查询信息", notes="查询信息")
    public List<Test> findAll(){
        List<Test> list = testServiceImpl.selectList(new EntityWrapper());
        return list;
    }
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    @ApiOperation(value="获取详细信息", notes="获取详细信息")
    public Test detail(String id){
        return testServiceImpl.selectOne(id);
    }
}
