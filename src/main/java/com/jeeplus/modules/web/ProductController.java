package com.jeeplus.modules.web;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.service.ProductService;
import com.jeeplus.modules.service.UserPService;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = {"findProductAll"})
    public JSONObject findProductAll(String productType, String productName, String specs, int pageNo) {

        PageInfo<MapEntity> studentPageInfo = productService.findAll(productType, productName, specs, pageNo);
        return ToJsonUtil.buildJsonRs(true, "", studentPageInfo);
    }


    @RequestMapping(value = {"save"})
    public JSONObject save(String id, String productType, String productName, String specs, String price) {
        try {
            MapEntity entity = new MapEntity();
            entity.put("id", id);
            entity.put("productType", productType);
            entity.put("productName", productName);
            entity.put("specs", specs);
            entity.put("price", price);

            if (id != null && id != "") {
                productService.updatePruduct(entity);
            } else {
                productService.insertPruduct(entity);
            }
            return ToJsonUtil.buildJsonRs(true, "添加成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return ToJsonUtil.buildJsonRs(false, "添加失败", "");
        }
    }


    @RequestMapping(value = {"deletetPruduct"})
    public JSONObject deletetPruduct(String id) {

        try {
            productService.deletetPruduct(id);
            return ToJsonUtil.buildJsonRs(true, "删除成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return ToJsonUtil.buildJsonRs(false, "失败", "");
        }

    }

}

