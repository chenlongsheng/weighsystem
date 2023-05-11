package com.jeeplus.modules.dao;


import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PersonDao {


    public List<MapEntity> getAllPerson(@Param(value = "name") String name, @Param(value = "officeId") String officeId,@Param(value = "cardNo") String cardNo);




    void insertPerson(MapEntity entity);

    void deletePersonById(@Param(value = "person_no") String person_no);



 //  void  updateMenuRole(@Param(value = "roleId") String roleId, @Param(value = "menuId") String menuId);


}
