package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.RoleServiceImpl;
import com.jeeplus.modules.service.UserPService;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private com.jeeplus.modules.service.UserPService userService;


    @RequestMapping(value = {"findUserAll"})
    public JSONObject findUserAll(String name, String officeId, Integer pageNo) {


        PageInfo<MapEntity> studentPageInfo = userService.findAll(name, officeId, pageNo);

        return ToJsonUtil.buildJsonRs(true, "", studentPageInfo);
    }


    @RequestMapping(value = {"save"})
    public JSONObject save(String id, String phone, String roleIds, String pId, String password,
                           String addr, String loginName, String name, String officeId, String time) {
        try {
            MapEntity entity = new MapEntity();
            entity.put("id", id);
            entity.put("phone", phone);
            entity.put("roleId", roleIds);
            entity.put("pId", ToJsonUtil.getUserId());
            entity.put("password", password);
            entity.put("addr", addr);
            entity.put("loginName", loginName);
            entity.put("name", name);
            entity.put("officeId", officeId);
            entity.put("time2", time);
            System.out.println(time);
//            if (id != null && id != "") {
//                userService.updateUser(entity);
//            } else {
            userService.insertUser(entity);
//            }
            return ToJsonUtil.buildJsonRs(true, "添加成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return ToJsonUtil.buildJsonRs(false, "添加失败", "");
        }
    }


    @RequestMapping(value = {"deleteUserById"})
    public JSONObject deleteUserById(String id, String delFlag) {

        try {
            userService.deleteUserById(id, delFlag);
            return ToJsonUtil.buildJsonRs(true, "删除成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return ToJsonUtil.buildJsonRs(false, "删除失败", "");
        }
    }


    @RequestMapping(value = {"updatePassById"})
    public JSONObject updatePassById(String id, String password) {

        try {
            userService.updatePassById(id, password);
            return ToJsonUtil.buildJsonRs(true, "成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return ToJsonUtil.buildJsonRs(false, "删除失败", "");
        }
    }

    @RequestMapping(value = {"updateSysPassword"})
    public JSONObject updateSysPassword(String oldSysPassword, String sysPassword) {


        try {
            int check = userService.updateSysPassword(oldSysPassword, sysPassword);
            if (check == 0) {
                return ToJsonUtil.buildJsonRs(false, "修改失败,旧密码错误", "");
            } else {
                return ToJsonUtil.buildJsonRs(true, "成功", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ToJsonUtil.buildJsonRs(false, "失败", "");
        }

    }


}

