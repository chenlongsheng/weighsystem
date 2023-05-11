package com.jeeplus.modules.dao;


import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RoleDao {


    public List<MapEntity> getAllRoles(@Param(value = "name") String name);

    void deleteRoleById(@Param(value = "id") String id);


    void updateRole(@Param(value = "id") String id, @Param(value = "name") String name);


    void insertRole(MapEntity entity);

    public List<MapEntity> selectMenuByRoleId(@Param(value = "roleId") String roleId);

   void  updateMenuRole(@Param(value = "roleId") String roleId, List<String> list);


}
