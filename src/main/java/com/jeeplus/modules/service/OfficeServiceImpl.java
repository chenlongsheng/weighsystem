package com.jeeplus.modules.service;

import com.jeeplus.modules.dao.OfficeDao;
import com.jeeplus.modules.dao.RoleDao;
import com.jeeplus.modules.model.MapEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OfficeServiceImpl {


    @Resource
    private OfficeDao officeDao;

    public List<MapEntity> getAllOffice() {
        return officeDao.getAllOffice();
    }


    public void updateOffice(String id, String name,String pId) {
        officeDao.updateOffice(id, name,pId);
    }


    public void insertOffice(MapEntity entity) {
        officeDao.insertOffice(entity);
    }


    public void deleteOfficeById(String id) {
        officeDao.deleteOfficeById(id);
    }
}
