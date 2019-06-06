package com.nrxtechnologies.hoto.Activities;

public class UserDetails {


    private String employee_Id,employee_Token;

    public UserDetails(String employee_Id,String employee_Token) {

        this.employee_Id = employee_Id;
        this.employee_Token=employee_Token;
           }
    public String getEmployee_Id() {
        return employee_Id;
    }
    public String getToken(){
        return employee_Token;
    }

}
