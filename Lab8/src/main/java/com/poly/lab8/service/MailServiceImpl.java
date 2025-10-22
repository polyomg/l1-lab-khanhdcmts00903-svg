package com.poly.lab8.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;
    
    // Hàng đợi (Queue)
    List<Mail> queue = new ArrayList<>(); 

    // Phương thức xếp Mail vào hàng đợi (Bài 2)
    @Override
    public void push(Mail mail){ 
        queue.add(mail); 
    }

    // Phương thức gửi Mail thực tế (Bài 1)
    @Override
    public void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage(); 
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // Ghi thông tin người gửi
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom()); 

            // Ghi thông tin người nhận
            		helper.setTo(mail.getTo());
            if (!this.isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            if (!this.isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            // Ghi tiêu đề và nội dung
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            // Đính kèm file
            String filenames = mail.getFilenames(); 
            if (!this.isNullOrEmpty(filenames)) { 
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }
            
            // Gửi Mail
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Phương thức chạy nền gửi mail (Bài 2)
    @Scheduled (fixedDelay = 500) 
    public void run() {
        while(!queue.isEmpty()) 
            try {
                this.send(queue.remove(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    // Phương thức kiểm tra chuỗi rỗng/null
    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().length() == 0);
    }
}