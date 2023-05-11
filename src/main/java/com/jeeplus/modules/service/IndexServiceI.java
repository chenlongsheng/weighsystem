package com.jeeplus.modules.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeeplus.modules.dao.IndexDao;
import com.jeeplus.modules.dao.PersonDao;
import com.jeeplus.modules.model.History;
import com.jeeplus.modules.model.MapEntity;
import com.jeeplus.modules.websoket.utils.SpringUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.record.TableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
public class IndexServiceI {


    @Resource
    private IndexDao indexDao;




    public void insertHistoryData(MapEntity entity) {
        indexDao.insertHistoryData(entity);
    }

    public List<MapEntity> getHistoryByTimes() {
        return indexDao.getHistoryByTimes();
    }


    public void replaceProduct(List<MapEntity> list,List<MapEntity> list1) {

        indexDao.replaceProduct(list);

        indexDao.inputDatas(list1);

    }









   /* public void forSaveBatch(List<History> list) {

        SqlSessionFactory sqlSessionFactory = SpringUtil.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);


        //  反射获取，获取Mapper
        History entityMapper = sqlSession.getMapper(History.class);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {


            indexDao.insertHistoryData1(list.get(i));
        }


        // 一次性提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时： " + (endTime - startTime));


    }*/


   /* public void forSaveBatch1(List<History> records) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            IndexDao mapper = session.getMapper(IndexDao.class);
            //自定义你的方法来获取需要插入的数据

            //BatchInsert
            BatchInsert<History> batchInsert = insert(records)
                    .into(table)
                    .map(id).toProperty("id")
                    .map(field1).toProperty("field1")
                    .map(field2).toProperty("field2")
                    .build()
                    .render(RenderingStrategy.MYBATIS3);
            batchInsert.insertStatements().stream().forEach(mapper::insert);
            session.commit();
        } finally {
            session.close();
        }
    }*/


    /**
     * @param entityList
     * @param batchSize
     * @return
     */
    //  @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(Collection<History> entityList, int batchSize) {
        try {
            int size = entityList.size();
            int idxLimit = Math.min(batchSize, size);
            int i = 1;
            //保存单批提交的数据集合
            List<History> oneBatchList = new ArrayList<>();
            for (Iterator<History> var7 = entityList.iterator(); var7.hasNext(); ++i) {
                History element = var7.next();
                oneBatchList.add(element);
                if (i == idxLimit) {
//                    userMapper.insertBatchSomeColumn(oneBatchList);
                    //每次提交后需要清空集合数据
                    oneBatchList.clear();
                    idxLimit = Math.min(idxLimit + batchSize, size);
                }
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }

}








