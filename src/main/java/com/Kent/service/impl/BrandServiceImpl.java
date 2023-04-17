package com.Kent.service.impl;

import com.Kent.mapper.BrandMapper;
import com.Kent.pojo.Brand;
import com.Kent.pojo.PageBean;
import com.Kent.service.BrandService;
import com.Kent.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;

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

    @Override
    public void deleteByIds(int[] ids) {
        // 2. 獲取 sqlSession 物件
        SqlSession sqlSession = factory.openSession();

        // 3. 獲取 BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 調用方法
        mapper.deleteById(ids);
        sqlSession.commit();

        // 5. 釋放資源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {

        // 2. 獲取 sqlSession 物件
        SqlSession sqlSession = factory.openSession();

        // 3. 獲取 BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 計算開始索引
        int begin = (currentPage -1 ) * pageSize;
        // 計算查詢條目數
        int size = pageSize;

        // 5. 查詢當前頁數據
        List<Brand> rows = mapper.selectByPage(begin, size);

        // 6. 查詢總紀錄數
        int totalCount = mapper.selectTotalCount();

        // 7. 封裝 PageBean 物件
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8. 釋放資源
        sqlSession.close();

        return pageBean;
    }
}
