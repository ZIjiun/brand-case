package com.Kent.service.impl;

import com.Kent.mapper.BrandMapper;
import com.Kent.pojo.Brand;
import com.Kent.service.BrandService;
import com.Kent.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    // 1. 創建 sqlSessionFactory 工廠物件
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        // 2. 或取 sqlSession 物件
        SqlSession sqlSession = factory.openSession();

        // 3. 獲取 BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 調用方法
        List<Brand> brands = mapper.selectAll();

        // 5. 釋放資源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        // 2. 獲取 sqlSession 物件
        SqlSession sqlSession = factory.openSession();

        // 3. 獲取 BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 調用方法
        mapper.add(brand);
        sqlSession.commit(); // 提交事務

        // 5. 釋放資源
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }
}
