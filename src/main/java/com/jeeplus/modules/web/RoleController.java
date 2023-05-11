package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.RoleServiceImpl;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;


    @RequestMapping(value = {"findAll"})
    public JSONObject findAll(String name) {

        return ToJsonUtil.buildJsonRs(true, "查询角色", roleService.findAll(name));

    }


    @RequestMapping(value = {"save"})
    public JSONObject save(String id, String name) {
        MapEntity entity = new MapEntity();
        entity.put("id", id);
        entity.put("name", name);
        if (id != null && id != "") {

            roleService.updateRole(id, name);
        } else {

            roleService.insertRole(entity);
        }
        return ToJsonUtil.buildJsonRs(true, "添加成功", "");
    }


    @RequestMapping(value = {"deleteRoleById"})
    public JSONObject deleteRoleById(String id) {
        roleService.deleteRoleById(id);
        return ToJsonUtil.buildJsonRs(true, "删除成功", "");
    }


    @RequestMapping(value = {"selectMenuByRoleId"})
    public List<MapEntity> selectMenuByRoleId(String roleId) {
        return roleService.selectMenuByRoleId(roleId);
    }


    @RequestMapping(value = {"updateMenuRole"})
    public JSONObject updateMenuRole(String roleId, String menuIds) {
        roleService.updateMenuRole(roleId, menuIds);

        return ToJsonUtil.buildJsonRs(true, "成功", "");
    }


}

