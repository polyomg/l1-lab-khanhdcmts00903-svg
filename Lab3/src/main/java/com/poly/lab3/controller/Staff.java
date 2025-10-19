package com.poly.lab3.controller;

import java.util.Date;

public class Staff {
    private String id;
    private String fullname;
    private String photo = "staff.jpg";
    private Boolean gender;
    private Date birthday = new Date();
    private Double salary = 12345.6789;
    private Integer level = 0;

    // --- Getter & Setter ---
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getGender() {
        return gender;
    }
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

    // --- Builder Pattern ---
    public static class Builder {
        private String id;
        private String fullname;
        private String photo = "staff.jpg";
        private Boolean gender;
        private Date birthday = new Date();
        private Double salary = 12345.6789;
        private Integer level = 0;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public Builder photo(String photo) {
            this.photo = photo;
            return this;
        }

        public Builder gender(Boolean gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder level(Integer level) {
            this.level = level;
            return this;
        }

        public Staff build() {
            Staff staff = new Staff();
            staff.id = this.id;
            staff.fullname = this.fullname;
            staff.photo = this.photo;
            staff.gender = this.gender;
            staff.birthday = this.birthday;
            staff.salary = this.salary;
            staff.level = this.level;
            return staff;
        }
    }

    // method để gọi builder()
    public static Builder builder() {
        return new Builder();
    }
}
