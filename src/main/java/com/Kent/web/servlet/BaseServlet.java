package com.Kent.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替換 HttpServlet，根據請求的路徑進行方法的分發
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 獲取請求的路徑
        StringBuffer url = req.getRequestURL();
        String uri = req.getRequestURI(); // /brand-case/brand/selectAll
//        System.out.println(url); // http://localhost:8080/brand-case/user/selectAll
//        System.out.println(uri); // /brand-case/user/selectAll

        // 2. 取得最後一段路徑(方法名稱)
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1); // selectAll
//        System.out.println(methodName); // selectAll

        // 3. 執行方法

        // 3.1 獲取 BrandServlet 字節碼物件 class
        // 反射是指對於任何一個Class類，不創建實體，但要調用子類方法，可以在「執行的時候」先獲取 class，就可以直接得到這個 class 的全部成分
        Class<? extends BaseServlet> cls = this.getClass();

        // 3.2 獲取方法 Method 物件
        // 誰調用我(this 所在的方法)，我(this)代表誰
//        System.out.println(this); // BrandServlet
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 執行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}