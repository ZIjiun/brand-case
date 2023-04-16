package com.Kent.service;

import com.Kent.pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 查詢所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 新增資料
     * @param brand
     */
    void add(Brand brand);

    /**
     * 更新品牌
     * @param brand
     */
    void update(Brand brand);

}
