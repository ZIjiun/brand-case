package com.Kent.web.servlet.old;


import com.Kent.pojo.Brand;
import com.Kent.service.BrandService;
import com.Kent.service.impl.BrandServiceImpl;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 1. 接收品牌資料
        // 因為接收的是 JSON 資料，所以需要使用 getReader
        BufferedReader reader = req.getReader();

        // 2. 因為post提交，在請求體裡邊，參數，但是隻有一行，所以只讀一行
        String params = reader.readLine();

        // 轉為 Brand 物件
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2. 調用 service 增加
        brandService.add(brand);

        // 3. 響應成功標示
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
