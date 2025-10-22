package com.poly.lab8.Interceptor;

import com.poly.lab8.model.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, 
                             HttpServletResponse response, 
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        Account user = (Account) session.getAttribute("user");

        // 1. Kiểm tra Đăng nhập
        if (user == null) { // chưa đăng nhập
            session.setAttribute("securityUri", uri); // Lưu URI bị chặn
            response.sendRedirect("/auth/login");
            return false; // Chặn request
        }

        // 2. Kiểm tra Vai trò Admin
        // Logic này áp dụng cho các URI bắt đầu bằng "/admin" VÀ không phải là trang ngoại lệ (như /admin/home/index)
        if (uri.startsWith("/admin") && !uri.equals("/admin/home/index")) {
             if (!user.isAdmin()) { // không phải admin
                
                // Xóa URI bảo mật để tránh vòng lặp chuyển hướng
                session.removeAttribute("securityUri"); 
                
                // Chuyển hướng về trang đăng nhập với thông báo lỗi
                response.sendRedirect("/auth/login?message=Access%20Denied.%20Admin%20required.");
                return false; // Chặn request
            }
        }
        
        // Nếu đã đăng nhập và có quyền truy cập
        return true;
    }
}