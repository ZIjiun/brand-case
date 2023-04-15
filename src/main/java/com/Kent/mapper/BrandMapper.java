package com.Kent.mapper;

import com.Kent.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

    /**
     * 查詢所有
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    public List<Brand> selectAll();

    /**
     * 新增資料
     * @param brand
     */
    @Insert("insert into tb_brand values (null, #{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    public void add(Brand brand);
}
