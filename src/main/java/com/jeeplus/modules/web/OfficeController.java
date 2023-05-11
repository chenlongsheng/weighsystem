package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.OfficeServiceImpl;
import com.jeeplus.modules.service.RoleServiceImpl;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offices")
@CrossOrigin(origins = "*")
public class OfficeController {

    @Autowired
    private OfficeServiceImpl officeService;

    @RequestMapping(value = {"findAll"})
    public JSONObject findAll() {
        return ToJsonUtil.buildJsonRs(true, "查询部门", officeService.getAllOffice());
    }


    @RequestMapping(value = {"save"})
    public JSONObject save(String id, String name, String pId) {
        MapEntity entity = new MapEntity();
        entity.put("id", id);
        entity.put("name", name);
        entity.put("pId", pId);

        if (id != null && id != "") {
            officeService.updateOffice(id, name, pId);
        } else {
            officeService.insertOffice(entity);
        }
        return ToJsonUtil.buildJsonRs(true, "添加成功", "");
    }


    @RequestMapping(value = {"deleteOfficeById"})
    public JSONObject deleteOfficeById(String id) {
        officeService.deleteOfficeById(id);
        return ToJsonUtil.buildJsonRs(true, "删除成功", "");
    }
}

