package com.poly.lab7.service; // Gói gợi ý

import jakarta.servlet.http.HttpSession; // Cần import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    /**
     * Đọc giá trị từ session attribute
     * @param name tên session attribute
     * @param defaultValue giá trị mặc định nếu không tồn tại
     * @return giá trị của attribute hoặc giá trị mặc định
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String name, T defaultValue) {
        T value = (T) session.getAttribute(name);
        return value != null ? value : defaultValue;
    }

    /**
     * Ghi giá trị vào session attribute
     * @param name tên session attribute
     * @param value giá trị cần ghi
     */
    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    /**
     * Xóa session attribute
     * @param name tên session attribute
     */
    public void remove(String name) {
        session.removeAttribute(name);
    }
}