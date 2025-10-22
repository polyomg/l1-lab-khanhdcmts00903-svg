package com.poly.lab4.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @NotEmpty(message = "Chưa nhập email!")
    @Email(message = "Email không đúng định dạng!")
    private String email;

    @NotEmpty(message = "Chưa nhập họ và tên!")
    private String fullname;

    @NotNull(message = "Chưa chọn giới tính!")
    private Boolean gender;

    @NotNull(message = "Chưa nhập ngày sinh!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "Chưa nhập lương!")
    @Min(value = 1000, message = "Lương tối thiểu phải là 1000!")
    private Double salary;

    private String photo = "photo.jpg";
}
