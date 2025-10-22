package com.poly.lab5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    
    @Autowired
    HttpServletRequest request;

    /** Đọc chuỗi giá trị của tham số */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }

    /** Đọc số nguyên giá trị của tham số */
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue; 
        }
    }

    /** Đọc số thực giá trị của tham số */
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        try {
            return value != null ? Double.parseDouble(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /** Đọc giá trị boolean của tham số */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    /** Đọc giá trị thời gian của tham số */
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(false);
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Lỗi sai định dạng thời gian cho tham số " + name + ": " + e.getMessage());
        }
    }

    /** Lưu file upload vào thư mục */
    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        // Lưu ý: getRealPath() không được khuyến khích dùng trong Jakarta Servlet (Tomcat 10+)
        // Trong môi trường Spring Boot, bạn nên dùng ResourceLoader hoặc Paths để quản lý file.
        // Tuy nhiên, để tuân theo yêu cầu của bài lab, ta vẫn dùng.
        String uploadPath = request.getServletContext().getRealPath(path);
        File dir = new File(uploadPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage(), e);
        }
    }
}