/**
 *
 */
package com.jeeplus.modules.web;


import com.jeeplus.modules.dao.UserPDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author admin
 */
@Controller
@CrossOrigin(origins = "*")
public class BanlanceController {

    @Autowired
    public UserPDao userDao;


    @RequestMapping(value = {"startEnd"})
    @ResponseBody
    public void startEnd(Integer state) {

//        userDao.getUsers();
//        System.out.println(userDao.getUsers().toString());
    }

}
