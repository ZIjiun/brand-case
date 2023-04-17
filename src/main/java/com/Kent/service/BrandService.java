package com.Kent.service;

import com.Kent.pojo.Brand;
import com.Kent.pojo.PageBean;

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

    /**
     * 批量刪除
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 分頁查詢
     * @param currentPage 當前頁碼
     * @param pageSize 展示條數
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

}
