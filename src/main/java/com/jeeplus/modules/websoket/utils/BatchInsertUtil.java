package com.jeeplus.modules.websoket.utils;



import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Mybatis 批量新增工具类
 */
@Slf4j
public class BatchInsertUtil {

    /**
     * 批量新增方法
     *
     * @param list       要新增的集合
     * @param clazz      Mapper类
     * @param biConsumer 对应的单条新增方法
     * @param <M>        mapper类型
     * @param <T>        结合元素类型
     */
    public static  <M, T> void batchInsert(List<T> list, Class<M> clazz, BiConsumer<M, T> biConsumer) {
        if (list == null || list.size() == 0) {
            log.info("BatchInsertUtil batchInsert list data is null!");
            return;
        }
        SqlSessionFactory sqlSessionFactory = SpringUtil.getBean(SqlSessionFactory.class);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,true);
        try {
            M mapper = session.getMapper(clazz);
            list.forEach(a -> {
                biConsumer.accept(mapper, a);
            });
            session.commit();
            session.clearCache();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("BatchInsertUtil batchInsert is exception！clazz={}", clazz.getName(), e);
            session.rollback();
        } finally {
            session.close();
        }
    }
}