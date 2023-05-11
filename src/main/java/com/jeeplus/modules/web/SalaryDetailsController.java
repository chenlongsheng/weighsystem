package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.dao.SalaryDetailsDao;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.RoleServiceImpl;
import com.jeeplus.modules.service.SalaryDetailsService;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Salary")
@CrossOrigin(origins = "*")
public class SalaryDetailsController {
    // 1. 得到日志对象
    private Logger logger = LoggerFactory.getLogger(SalaryDetailsController.class);
    @Autowired
    private SalaryDetailsService salaryDetailsService;

    @Resource
    private SalaryDetailsDao salaryDetailsDao;

    //查询工资明细
    @RequestMapping(value = {"SalaryDetails"})
    public JSONObject SalaryDetails(String personNo, String personName, String productName, String specs, String proType, String time, Integer pageNo) {

        PageInfo<MapEntity> studentPageInfo = salaryDetailsService.getSalaryDetails(personNo, personName, productName, specs, proType, time, pageNo);

        return ToJsonUtil.buildJsonRs(true, "", studentPageInfo);
    }

    //查询工资汇总
    @RequestMapping(value = {"getSalaryTotals"})
    public JSONObject getSalaryTotals(String officeName, String productName, String specs, String proType, String time, Integer pageNo) {


        PageInfo<MapEntity> studentPageInfo = salaryDetailsService.getSalaryTotals(officeName, productName, specs, proType, time, pageNo);

        return ToJsonUtil.buildJsonRs(true, "", studentPageInfo);
    }


    @RequestMapping(value = {"exportDetailsSalary"})   //导出工资明细
    @ResponseBody
    public void exportDetailsSalary(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    String personNo, String personName, String productName, String specs, String proType, String time) throws Exception {

        List<MapEntity> getSalaryDetails = salaryDetailsDao.getSalaryDetails(personNo, personName, productName, specs, proType, time);
        logger.debug("日志级别: degue");

        salaryDetailsService.exportChannelRportList(httpServletRequest, httpServletResponse, getSalaryDetails);
    }

}

