package com.nrxtechnologies.hoto.models;

public class ApproveModel
{
    String Date,employee,status,remarks;

    public ApproveModel(String date, String employee, String status, String remarks) {
        Date = date;
        this.employee = employee;
        this.status = status;
        this.remarks = remarks;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
