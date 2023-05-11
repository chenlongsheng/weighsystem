package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.dao.IndexDao;
import com.jeeplus.modules.dao.PersonDao;
import com.jeeplus.modules.dao.ProductDao;
import com.jeeplus.modules.model.History;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.IndexServiceI;
import com.jeeplus.modules.service.PersonServiceImpl;
import com.jeeplus.modules.websoket.utils.BatchInsertUtil;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import javafx.scene.web.WebHistory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/index")
@CrossOrigin(origins = "*")
public class IndexController {

    @Autowired
    IndexServiceI indexService;

    @Autowired
    IndexDao indexDao;

    @Autowired
    private com.jeeplus.modules.dao.PersonDao personDao;

    @Autowired
    private com.jeeplus.modules.dao.ProductDao productDao;

    @Autowired
    Executor asyncServiceExecutor;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = {"save"})
    public JSONObject save(String productId, String personNo, String weighNum) {

        //id,name,person_no,card_no,,phone,addr
        MapEntity entity = new MapEntity();
        entity.put("personNo", personNo);
        entity.put("productId", productId);
        entity.put("weighNum", weighNum);

        indexService.insertHistoryData(entity);


        return ToJsonUtil.buildJsonRs(true, "添加成功", "");
    }

    @Bean
    public RestTemplate restTemplate() {
        //发起请求的工具
        return new RestTemplate();
    }

    @RequestMapping(value = {"forSaveBatch"})
    public Object forSaveBatch() throws InterruptedException {

        List<History> list = new ArrayList<History>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            History h = new History();
            h.setPersonNo("1");
            h.setProductId("2");
            h.setWeighNum("5");
            list.add(h);
//indexDao.insertHistoryData1(h);
        }
        //  indexDao.insertHistoryData2(list);
        //    asyncMethod(list, indexDao, asyncServiceExecutor);

        List<History> listtest = new ArrayList<History>();
        for (int i = 0; i < list.size(); i++) {

            if (listtest.size() >= 99 || list.size() - 1 == i) {
                listtest.add(list.get(i));

                asyncMethod(listtest, indexDao, asyncServiceExecutor);
                //
                listtest = new ArrayList<History>();
            } else {
                listtest.add(list.get(i));
            }
        }

        //      indexService.forSaveBatch(list);
        //  BatchInsertUtil.batchInsert(list, IndexDao.class, IndexDao::insertHistoryData1);
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时： " + (endTime - startTime));
        return ToJsonUtil.buildJsonRs(true, "添加成功", "");
    }


    public static CompletableFuture<String> asyncMethod(List<History> list1, IndexDao indexDao, Executor asyncServiceExecutor) {
        return CompletableFuture.supplyAsync(() -> {
            // 执行长时间运行的任务
            System.out.println(list1.toString());
            indexDao.insertHistoryData2(list1);


            return null;
        }, asyncServiceExecutor);
    }


    public static void insert(MapEntity messages, IndexServiceI indexServiceI) {

        List<MapEntity> getAllPruducts = (List<MapEntity>) messages.get("getAllPruducts");

        List<MapEntity> getHistoryByTimes = (List<MapEntity>) messages.get("getHistoryByTimes");

        indexServiceI.replaceProduct(getAllPruducts, getHistoryByTimes);

        System.out.println("插入数据");
    }

    @RequestMapping(value = {"yun"})
    public MapEntity yun(@RequestBody MapEntity messages) {
//插入数据
        insert(messages, indexService);

        //回传数据
        MapEntity en = new MapEntity();
        List<MapEntity> getAllPerson = personDao.getAllPerson(null, null, null);
        List<MapEntity> getAllPruductsyun = productDao.getAllPruducts(null, null, null);
        en.put("getAllPerson", getAllPerson);
        en.put("getAllPruducts", getAllPruductsyun);
        return en;
    }


    @RequestMapping(value = {"bendi"})
    public JSONObject bendi(String productId, String personNo, String weighNum) {

        List<MapEntity> getAllPruducts = productDao.getAllPruducts(null, null, null);
        MapEntity en = new MapEntity();
        en.put("getAllPruducts", getAllPruducts);
        en.put("getHistoryByTimes", indexService.getHistoryByTimes());
        // Object o = restTemplate.getForObject("http://127.0.0.1:8070/index/yun", Object.class,);
        MapEntity data = restTemplate.postForObject("http://127.0.0.1:8070/index/yun", en, MapEntity.class);

        insertPersonProduct(data, indexService);

        System.out.println("data:=====" + data);
        return null;

    }

    public static void insertPersonProduct(MapEntity data, IndexServiceI indexService) {


        //处理回传数据
        List<MapEntity> getAllPerson = (List<MapEntity>) data.get("getAllPerson");


        List<MapEntity> getAllPruductsyun = (List<MapEntity>) data.get("getAllPruducts");


    }

}