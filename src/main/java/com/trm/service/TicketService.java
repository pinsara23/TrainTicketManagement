/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trm.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.trm.dto.BookingDto;
import com.trm.dto.TrainDto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TicketService {
    
    
    private List<TrainDto> trains = new ArrayList<>();
    private List<BookingDto> bookings = new ArrayList<>();
    private final String TRAIN_FILE = "traindetails.csv";
    private final String BOOKING_FILE = "bookings.csv";
    
    
    public TicketService(){
        readDataLineByLine(TRAIN_FILE);
    }
    
    
    
    //output train details
    public void outputTrains(){
        for (TrainDto train : trains) {
            //System.out.println(train.getTrainId()+","+train.getRoute());
            System.out.println(train.getTrainId()+","+train.getRoute()+","+train.getTrainName()+","+train.getAvailableSeats());
        }
    }
    
    
    //read train details
    public void readDataLineByLine(String file)
{

    try {

        // Create an object of filereader
  
        FileReader filereader = new FileReader(file);

        // create csvReader object 
  
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;

        while ((nextRecord = csvReader.readNext()) != null) {

              
            TrainDto train = new TrainDto(nextRecord[0].trim(), nextRecord[1].trim(), nextRecord[2].trim(), Integer.parseInt(nextRecord[3].trim()));
            trains.add(train);
            
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    
    
}
    //book ticket
    public boolean bookTicket(BookingDto bookDto){
        
        boolean result = false;
        if (bookDto.getSeats()>4 || bookDto.getSeats()<1) {
            System.out.println("You cant book more than 4 seats");
        }
        
        for (TrainDto train : trains) {
            if (bookDto.getTrainId().equalsIgnoreCase(train.getTrainId())) {
                if (train.getAvailableSeats()<bookDto.getSeats()) {
                    System.out.println("There are no such amount of seatsnin train");
                    break;
                }
                train.setAvailableSeats(train.getAvailableSeats()-1);
                bookings.add(bookDto);
                result = true;
                break;
            }
            System.out.println("Train id is invalid");
        }
        return result;
    }
    
    //read all ticket details
    public void outputBookings(){
        for (BookingDto book : bookings) {
            System.out.println(book.getBookingId()+","+book.getName()+","+book.getNic()+","+book.getTrainId());
        }
    }
    
    //edit ticket details
    public boolean editTicket(BookingDto dto){
        boolean result  = false;
        for (BookingDto book: bookings) {
            if (dto.getBookingId()==book.getBookingId()) {
                if (dto.getContactNo()!=null) {
                    book.setContactNo(dto.getContactNo());
                }
                if (dto.getName()!=null) {
                    book.setName(dto.getName());
                }
                
                if(dto.getNic()!=null)
                {
                    book.setNic(dto.getNic());
                }
                if(dto.getTrainId()!=null)
                {
                    book.setTrainId(dto.getTrainId());
                }
                result = true;
                break;
                
            }
            System.out.println("Book id is invalid");
        }
        return result;
    }
    
    //delete ticket details
    public boolean deleteTicket(int bookingId){
        boolean result = false;
        for (BookingDto book: bookings) {
            if (bookingId == book.getBookingId()) {
                for (TrainDto train : trains) {
                    if (book.getTrainId().equalsIgnoreCase(train.getTrainId())) {
                        train.setAvailableSeats(train.getAvailableSeats()+1);
                        break;
                    }
                }
                book.setStatus(false);
                result = true;
                break;
            }
        }
        return result;
    }
    
    //add bookings to csv
    public void saveaBookingsToCsv(BookingDto dto){
    
        try (CSVWriter writer = new CSVWriter(new FileWriter(BOOKING_FILE)) ){
            String [] row = {String.valueOf(dto.getBookingId()),dto.getName(),dto.getNic()};
        } catch (Exception e) {
        }
    
    }
    
    
    
}
