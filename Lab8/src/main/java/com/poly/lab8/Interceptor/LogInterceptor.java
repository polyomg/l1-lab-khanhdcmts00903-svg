package com.poly.lab8.Interceptor;

import com.poly.lab8.model.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date; 

@Component
public class LogInterceptor implements HandlerInterceptor { 

    @Override
    public boolean preHandle(HttpServletRequest request, 
                             HttpServletResponse response, 
                             Object handler) throws Exception {

        // Lấy thông tin user từ session (được lưu sau khi đăng nhập thành công ở Bài 4)
        Account user = (Account) request.getSession().getAttribute("user");
        
        // Chỉ ghi log nếu user đã đăng nhập (điều kiện này đã được AuthInterceptor đảm bảo)
        if (user != null) {
            // Ghi nhận địa chỉ URI, thời gian truy xuất và họ tên người dùng
            System.out.println(request.getRequestURI() + 
                               "," + new Date() + 
                               ", " + user.getFullname());
        }
        
        return true; 
    }
}