/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trm.dto;

/**
 *
 * @author Admin
 */
public class BookingDto {
    
    private int bookingId;
    private String nic;
    private String name;
    private String contactNo;
    private String trainId;
    private int seats;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    public int getBookingId() {
        return bookingId;
    }

    public String getTrainId() {
        return trainId;
    }

    public int getSeats() {
        return seats;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    

    public String getNic() {
        return nic;
    }

    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "UserDto{" + "nic=" + nic + ", name=" + name + ", contactNo=" + contactNo + '}';
    }
    
    
    
    
}
