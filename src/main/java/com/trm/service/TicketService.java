/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trm.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.trm.dto.BookingDto;
import com.trm.dto.TrainDto;
import org.jetbrains.annotations.NotNull;

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
    
    
    private static List<TrainDto> trains = new ArrayList<>();
    private static List<BookingDto> bookings = new ArrayList<>();
    private static final String TRAIN_FILE = "traindetails.csv";
    private static final String BOOKING_FILE = "bookings.csv";
    
    
    public TicketService(){
        readDataLineByLine();
    }
    
    
    
    //output train details
    public void outputTrains(){

        System.out.println("Train ID    |   Route   |   Train Name  |   Available Seats");
        for (TrainDto train : trains) {

            System.out.println(train.getTrainId()+"         |   "+train.getRoute()+"    |   "+train.getTrainName()+"    |   "+train.getAvailableSeats());
        }
    }
    
    
    //read train details
    public void readDataLineByLine()
    {
        try {

            // Create an object of filereader
            FileReader filereader = new FileReader(TRAIN_FILE);
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

    //write booking details to csv
    public void writeToCsv() throws IOException {

        FileWriter writeFile = new FileWriter(BOOKING_FILE);
        try (CSVWriter writer = new CSVWriter(writeFile)) {
            // Write header
            String[] header = { "BookingId", "Nic", "Name", "ContactNo", "TrainId", "Seats" };
            writer.writeNext(header);

            // Write data
            for (BookingDto booking : bookings) {
                String[] data = {
                        String.valueOf(booking.getBookingId()),
                        booking.getNic(),
                        booking.getName(),
                        booking.getContactNo(),
                        booking.getTrainId(),
                        String.valueOf(booking.getSeats())
                };
                writer.writeNext(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //book ticket
    public boolean bookTicket(@NotNull BookingDto bookDto){
        
        boolean result = false;
        if (bookDto.getSeats()>4 || bookDto.getSeats()<1) {
            System.out.println("You cant book more than 4 seats");
        }
        
        for (TrainDto train : trains) {
            if (bookDto.getTrainId().equalsIgnoreCase(train.getTrainId())) {
                if (train.getAvailableSeats()<bookDto.getSeats()) {
                    System.out.println("There are no such amount of seats in train");
                    break;
                }
                train.setAvailableSeats(train.getAvailableSeats()-1);
                bookings.add(bookDto);
                result = true;
                break;
            }
        }
        if (!result){System.out.println("Train id is invalid");}
        return result;
    }
    
    //read all ticket details
    public void outputBookings(){
        System.out.println("Booking ID | Name | NIC | Train ID | Contact No | Seats | Status");
        for (BookingDto book : bookings) {
            System.out.println(book.getBookingId()+"    |   "+book.getName()+"   |   "+book.getNic()+"  |   "+book.getTrainId()+"   |   "+book.getContactNo()+"   |   "+book.getSeats()+"   |   "+book.isStatus());
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

                result = true;
                break;
                
            }

        }
        if (!result){System.out.println("Book id is invalid");}
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
    

}
