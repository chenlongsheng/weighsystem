package com.jeeplus.modules.dao;


import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SalaryDetailsDao {


    List<MapEntity> getSalaryDetails(@Param(value = "personNo") String personNo,
                                     @Param(value = "personName") String personName,
                                     @Param(value = "productName") String productName,
                                     @Param(value = "specs") String specs,
                                     @Param(value = "proType") String proType,
                                     @Param(value = "time") String time);


     List<MapEntity> getSalaryTotals(
                                      @Param(value = "officeName") String officeName,
                                      @Param(value = "productName") String product ,
                                      @Param(value = "specs") String specs,
                                      @Param(value = "proType") String proType,
                                      @Param(value = "time") String time);
}