package com.blibli.pos_minimarket.Model;

public class Employee {

    private Integer employee_Id;
    private String password;
    private String name;
    private Role role;
    private String email;
    private String status;

    public Employee() {
    }

    public Employee(Integer employee_Id, String password, String name, Role role, String email, String status) {
        this.employee_Id = employee_Id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
        this.status = status;
    }

    public Integer getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(Integer employee_Id) {
        this.employee_Id = employee_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
