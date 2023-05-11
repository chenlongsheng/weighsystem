package com.jeeplus.modules.dao;


import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProductDao {


    List<MapEntity> getAllPruducts(@Param(value = "productType") String productType, @Param(value = "productName") String productName, @Param(value = "specs") String specs);

    void insertPruduct(MapEntity entity);

    void updatePruduct(MapEntity entity);

   void  deletetPruduct(@Param(value = "id") String id);

}
