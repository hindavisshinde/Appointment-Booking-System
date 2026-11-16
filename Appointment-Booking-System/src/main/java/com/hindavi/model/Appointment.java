package com.hindavi.model;
import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int id, customerId;
    private String customerName, email, phone, serviceName, status, notes;
    private Date appointmentDate;
    private Time appointmentTime;

    public int getId(){return id;} public void setId(int v){id=v;}
    public int getCustomerId(){return customerId;} public void setCustomerId(int v){customerId=v;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getServiceName(){return serviceName;} public void setServiceName(String v){serviceName=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public Date getAppointmentDate(){return appointmentDate;} public void setAppointmentDate(Date v){appointmentDate=v;}
    public Time getAppointmentTime(){return appointmentTime;} public void setAppointmentTime(Time v){appointmentTime=v;}
}
