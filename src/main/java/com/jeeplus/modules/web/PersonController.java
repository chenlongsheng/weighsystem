package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.PersonServiceImpl;
import com.jeeplus.modules.service.RoleServiceImpl;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;


    @RequestMapping(value = {"findPersonAll"})
    public JSONObject findPersonAll(String name, String officeId,String cardNo, Integer pageNo) {


        PageInfo<MapEntity> studentPageInfo = personService.findAll(name, officeId, cardNo,pageNo);

        return ToJsonUtil.buildJsonRs(true, "", studentPageInfo);
    }


    @RequestMapping(value = {"save"})
    public JSONObject save(String name, String personNo, String cardNo, String officeId, String phone, String addr, String time) {

        //id,name,person_no,card_no,,phone,addr
        MapEntity entity = new MapEntity();
        entity.put("person_no", personNo);

        entity.put("time", time);
        entity.put("name", name);
        entity.put("card_no", cardNo);
        entity.put("office_id", officeId);
        entity.put("phone", phone);
        entity.put("addr", addr);


        personService.insertPerson(entity);

        return ToJsonUtil.buildJsonRs(true, "添加成功", "");
    }

    @RequestMapping(value = {"deletePersonById"})
    public JSONObject deletePersonById(String personNo) {

        personService.deletePersonById(personNo);

        return ToJsonUtil.buildJsonRs(true, "delete成功", "");

    }


}

