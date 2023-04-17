package com.Kent.web.servlet;

import com.Kent.pojo.Brand;
import com.Kent.pojo.PageBean;
import com.Kent.service.BrandService;
import com.Kent.service.impl.BrandServiceImpl;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 調用 service 查詢
        List<Brand> brands = brandService.selectAll();

        // 2. 轉為 JSON
        String jsonString = JSON.toJSONString(brands);

        // 3. 寫數據
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    /**
     * 批量刪除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收資料 [1,2,3]
        BufferedReader reader = req.getReader();
        String params = reader.readLine(); // // JSON 字串
        System.out.println(params);


        // 轉為 int[]
         int[] ids = JSON.parseObject(params, int[].class);

        // 2. 調用 service 增加
        brandService.deleteByIds(ids);

        // 3. 響應成功標示
        resp.getWriter().write("success");
    }

    /**
     * 分頁查詢
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 接收 當前頁碼 和 每頁展示條數 ex: url?currentPage=1&pagesize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 2. 調用 service 查詢
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        // 3. 轉為 JSON
        String jsonString = JSON.toJSONString(pageBean);

        // 3. 寫數據
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 分頁條件查詢
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 接收 當前頁碼 和 每頁展示條數 ex: url?currentPage=1&pagesize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 獲取查詢條件物件
        BufferedReader br = req.getReader();
        String params = br.readLine(); // JSON 字串

        // 轉為 Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2. 調用 service 查詢
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        // 3. 轉為 JSON
        String jsonString = JSON.toJSONString(pageBean);

        // 3. 寫數據
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
