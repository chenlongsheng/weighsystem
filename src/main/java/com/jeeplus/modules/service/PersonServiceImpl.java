package com.jeeplus.modules.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.dao.OfficeDao;
import com.jeeplus.modules.dao.PersonDao;
import com.jeeplus.modules.dao.RoleDao;
import com.jeeplus.modules.model.MapEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PersonServiceImpl {


    @Resource
    private PersonDao personDao;



    public PageInfo<MapEntity> findAll(String name, String OfficeId,String cardNo, int pageNo) {

        PageHelper.startPage(pageNo, 10);

        List<MapEntity> historyDatas = personDao.getAllPerson(name, OfficeId,cardNo);

        PageInfo<MapEntity> pageInfo = new PageInfo<>(historyDatas);

        return pageInfo;

    }




    public void insertPerson(MapEntity entity) {
        personDao.insertPerson(entity);
    }



    public void deletePersonById(String person_no ) {
        personDao.deletePersonById(person_no);
    }




}
