package com.jeeplus.modules.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.dao.RoleDao;
import com.jeeplus.modules.dao.SalaryDetailsDao;
import com.jeeplus.modules.model.MapEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class SalaryDetailsService {

    @Resource
    private SalaryDetailsDao salaryDetailsDao;

 //  private static  Logger logger = (Logger) LoggerFactory.getLogger(SalaryDetailsService.class);
    public PageInfo<MapEntity> getSalaryDetails(String personNo, String personName, String productName, String specs, String proType, String time, Integer pageNo) {

        PageHelper.startPage(pageNo, 10);

        List<MapEntity> historyDatas = salaryDetailsDao.getSalaryDetails(personNo, personName, productName, specs, proType, time);

        PageInfo<MapEntity> pageInfo = new PageInfo<>(historyDatas);
        return pageInfo;
    }


    public PageInfo<MapEntity> getSalaryTotals(String officeName, String productName, String specs, String proType, String time, Integer pageNo) {

        PageHelper.startPage(pageNo, 10);

        List<MapEntity> historyDatas = salaryDetailsDao.getSalaryTotals(officeName, productName, specs, proType, time);

        PageInfo<MapEntity> pageInfo = new PageInfo<>(historyDatas);

        return pageInfo;

    }


    public void exportChannelRportList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<MapEntity> list) throws Exception {




        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("光纤分段统计表");
// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

        HSSFRow row = sheet.createRow((int) 0);
// 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        sheet.setDefaultColumnWidth(20);
        HSSFFont nameRowFont = wb.createFont();
        nameRowFont.setFontName("微软雅黑");
        nameRowFont.setFontHeightInPoints((short) 12);// 设置字体大小
        nameRowFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
        style.setFont(nameRowFont);

        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("员工工号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("员工姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("产品类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("品名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("规格");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("框数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("重量（kg）");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("单价（元/kg）");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("工资（元）");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("生产日期");
        cell.setCellStyle(style);


        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        sheet.setDefaultColumnWidth(20);
        HSSFFont nameRowFont1 = wb.createFont();
        nameRowFont1.setFontName("微软雅黑");
        nameRowFont1.setFontHeightInPoints((short) 12);// 设置字体大小
        style1.setFont(nameRowFont1);

        java.util.Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println(sdf.format(day));
        String fileName = sdf.format(day) + "_工资明细统计表";

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            Map excel = list.get(i);
            // 第四步，创建单元格，并设置值
            cell = row.createCell((short) 0);
            cell.setCellValue((String) excel.get("personNo"));
            cell.setCellStyle(style1);
            cell = row.createCell((short) 1);
            cell.setCellValue((String) excel.get("personName"));
            cell.setCellStyle(style1);
            cell = row.createCell((short) 2);
            cell.setCellValue((String) excel.get("proType"));
            cell.setCellStyle(style1);
            cell = row.createCell((short) 3);
            cell.setCellValue((String) excel.get("productName"));
            cell.setCellStyle(style1);
            cell = row.createCell((short) 4);
            cell.setCellValue((String) excel.get("specs"));
            cell.setCellStyle(style1);
            cell = row.createCell((short) 5);
            cell.setCellValue((String) excel.get("count").toString());
            cell.setCellStyle(style1);
            cell = row.createCell((short) 6);
            cell.setCellValue(excel.get("weighNum").toString());
            cell.setCellStyle(style1);

            cell = row.createCell((short) 7);
            cell.setCellValue((String) excel.get("price").toString());
            cell.setCellStyle(style1);
            cell = row.createCell((short) 8);
            cell.setCellValue((String) excel.get("totalMoney").toString());
            cell.setCellStyle(style1);
            cell = row.createCell((short) 9);
            cell.setCellValue((String) excel.get("time"));
            cell.setCellStyle(style1);

        }
        // 响应到客户端
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("gb2312"), "ISO-8859-1") + ".xls");
        OutputStream os = httpServletResponse.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }
}
