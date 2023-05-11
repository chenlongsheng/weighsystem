package com.jeeplus.modules.dao;


import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OfficeDao {


    public List<MapEntity> getAllOffice();

    void deleteOfficeById(@Param(value = "id") String id);


    void updateOffice(@Param(value = "id") String id, @Param(value = "name") String name, @Param(value = "pId") String pId);


    void insertOffice(MapEntity entity);


}
