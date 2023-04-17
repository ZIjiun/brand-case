package com.Kent.mapper;

import com.Kent.pojo.Brand;
import org.apache.ibatis.annotations.*;

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

    /**
     * 更新品牌
     * @param brand
     */
    @Update("update tb_brand set brand_name = #{brandName}, company_name = #{companyName}, ordered = #{ordered}, description = #{description} where id = #{id}")
    public void update(Brand brand);

    /**
     * 批量刪除
     * @param id
     */
    void deleteById(@Param("ids") int [] id);

}
