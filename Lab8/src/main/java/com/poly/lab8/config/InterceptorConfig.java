package com.poly.lab8.config;

import com.poly.lab8.Interceptor.AuthInterceptor;
import com.poly.lab8.Interceptor.LogInterceptor; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;
    
    @Autowired 
    LogInterceptor logInterceptor; 

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Các URL cần bảo mật [cite: 269-272]
        String[] protectedUrls = {
            "/admin/**",
            "/account/change-password",
            "/account/edit-profile",
            "/order/**"
        };
        // URL ngoại lệ [cite: 273]
        String excludedUrl = "/admin/home/index";
        
        // 1. Cấu hình AuthInterceptor (Bài 5)
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(protectedUrls)
                .excludePathPatterns(excludedUrl);

        // 2. Cấu hình LogInterceptor (Bài 6)
        // LogInterceptor áp dụng cho CÙNG các URI được AuthInterceptor bảo vệ.
        registry.addInterceptor(logInterceptor)
                .addPathPatterns(protectedUrls)
                .excludePathPatterns(excludedUrl);
                
        // Lưu ý: Đảm bảo AuthInterceptor được đăng ký trước (nếu cần), 
        // nhưng với việc kiểm tra user != null trong LogInterceptor, thứ tự này là an toàn.
    }
}