/**
 *
 */
package com.jeeplus.modules.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.dao.ProductDao;
import com.jeeplus.modules.dao.UserPDao;
import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */

@Component
public class ProductService {

    @Resource
    private ProductDao productDao;


    public PageInfo<MapEntity> findAll(String productType, String name, String specs, int pageNo) {

        PageHelper.startPage(pageNo, 10);

        List<MapEntity> historyDatas = productDao.getAllPruducts(productType, name, specs);

        PageInfo<MapEntity> pageInfo = new PageInfo<>(historyDatas);

        return pageInfo;

    }


    public void updatePruduct(MapEntity entity) {

        productDao.updatePruduct(entity);
    }


    public void insertPruduct(MapEntity entity) {

        productDao.insertPruduct(entity);
    }

    public void deletetPruduct(String id) {

        productDao.deletetPruduct(id);
    }


}
