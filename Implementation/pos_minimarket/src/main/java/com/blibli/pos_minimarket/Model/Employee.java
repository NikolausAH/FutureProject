package com.blibli.pos_minimarket.Model;

public class Employee {

    private int employee_Id;
    private String password;
    private String name;
    private int role_id;
    private String role;
    private String email;

    public Employee() {
    }

    public Employee(int employee_Id, String password, String name, int role_id, String email) {
        this.employee_Id = employee_Id;
        this.password = password;
        this.name = name;
        this.role_id = role_id;
        this.email = email;
    }

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
