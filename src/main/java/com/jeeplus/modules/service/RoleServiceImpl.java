package com.jeeplus.modules.service;

import com.jeeplus.modules.dao.RoleDao;
import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoleServiceImpl {


    @Resource
    private RoleDao roleRepository;

    public List<MapEntity> findAll(String name) {
        return roleRepository.getAllRoles( name);
    }

    public void updateRole(String id, String name) {
        roleRepository.updateRole(id, name);
    }


    public void insertRole(MapEntity entity) {
        roleRepository.insertRole(entity);
    }


    public void deleteRoleById(String id) {
        roleRepository.deleteRoleById(id);
    }


    public List<MapEntity> selectMenuByRoleId(String roleId) {

        return roleRepository.selectMenuByRoleId(roleId);
    }


    public void updateMenuRole(String roleId, String menuIdS) {



        List<String> list  = Arrays.asList(menuIdS.split(","));





        roleRepository.updateMenuRole(roleId, list);
    }


}
