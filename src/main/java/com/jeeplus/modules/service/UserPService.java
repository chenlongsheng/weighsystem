/**
 *
 */
package com.jeeplus.modules.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.springframework.stereotype.Component;
import com.jeeplus.modules.dao.UserPDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */

@Component
public class UserPService {

    @Resource
    private UserPDao userPDao;


    public PageInfo<MapEntity> findAll(String name, String OfficeId, int pageNo) {

        PageHelper.startPage(pageNo, 10);

        List<MapEntity> historyDatas = userPDao.getUsers(ToJsonUtil.getUserId(),name, OfficeId);

        PageInfo<MapEntity> pageInfo = new PageInfo<>(historyDatas);

        return pageInfo;

    }


    public MapEntity getUsersById(String loginName) {

        return userPDao.getUsersById(loginName);
    }

    public void updateUser(MapEntity entity) {

        userPDao.updateUser(entity);
    }


    public void insertUser(MapEntity entity) {

        userPDao.insertUser(entity);
    }

    public void deleteUserById(String id, String delFlag) {
        if (delFlag != null && delFlag != "") {
            userPDao.updateFalgById(id, delFlag, null);
        } else {
            userPDao.deleteUserById(id);

        }
    }

    public void updatePassById(String id, String password) {

        userPDao.updateFalgById(id, null, password);

    }

    public int updateSysPassword(String oldPassword, String password) {

        int a = userPDao.updateSysPassword(oldPassword, password);

        System.out.println(a);
        return a;
    }


}
