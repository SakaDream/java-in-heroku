package com.sakadream.test.bean;

public class Employee {
    private Long id;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private Long salary;
    public Employee(Long id, String fullName, String address, String email, String phone, Long salary) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }
    public Employee(String fullName, String address, String email, String phone, Long salary) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }
    public Long getId() {
        return this.id;
    }
    public String getAddress() {
        return this.address;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }
    public Long getSalary() {
        return this.salary;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setSalary(Long salary) {
        this.salary = salary;
    }
}