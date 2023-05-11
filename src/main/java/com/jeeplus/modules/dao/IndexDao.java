package com.jeeplus.modules.dao;


import com.jeeplus.modules.model.History;
import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IndexDao   {



    void insertHistoryData(MapEntity entity);


    void insertHistoryData2(List<History> list);

    List<MapEntity> getHistoryByTimes();

    void replaceProduct(List<MapEntity> list);
 //  void  updateMenuRole(@Param(value = "roleId") String roleId, @Param(value = "menuId") String menuId);
 void   inputDatas(List<MapEntity> list);

}
