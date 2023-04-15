package com.Kent.service;

import com.Kent.pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 查詢所有
     * @return
     */
    List<Brand> selectAll();
}
