/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.trm.main;

import com.trm.dto.BookingDto;
import com.trm.service.TicketService;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class TrainTicketManagement {

    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        TicketService ticketService = new TicketService();
        int id = 0;
        int bookingId = 1;
        System.out.println("---------------------------------------------------------");
        System.out.println("Welcome to Ticket manager");
        System.out.println("---------------------------------------------------------");
        do {            
            System.out.println("Options \n 1.See Train timetable \n 2. Book a ticket \n 3. Edit Ticket details \n 4. Delete booked ticket"
                + "\n 5. See ticket Details \n 6.Exit and save ticket details \n Enter option id : ");
        
        id = scanner.nextInt();
        
        switch (id) {
            case 1:
                ticketService.outputTrains();
                break;
                
            case 2:
                BookingDto bookDto = new BookingDto();

                bookDto.setBookingId(bookingId++);
                System.out.println("Enter nic");
                bookDto.setNic(scanner.next());
                System.out.println("Enter name");
                bookDto.setName(scanner.next());
                System.out.println("Enter contact no");
                bookDto.setContactNo(scanner.next());
                System.out.println("Enter train id");
                bookDto.setTrainId(scanner.next());
                System.out.println("Enter no of seats");
                bookDto.setSeats(scanner.nextInt());
                bookDto.setStatus(true);
                
                
                boolean result  = ticketService.bookTicket(bookDto);
                if (result) {
                    System.out.println("Your booking id "+bookDto.getBookingId());
                }else{
                    System.out.println("Error in book ticket");
                }
                break;
            
            case 3:
                BookingDto editDto = new BookingDto();
                System.out.println("Enter booking id to edit = ");
                editDto.setBookingId(scanner.nextInt());
                System.out.println("Enter new nic = ");
                editDto.setNic(scanner.next());
                System.out.println("Enter new name = ");
                editDto.setName(scanner.next());
                System.out.println("Enter new contact no = ");
                editDto.setContactNo(scanner.next());
                ticketService.editTicket(editDto);
                break;

            case 4:
                System.out.println("Enter ticket id = ");
                ticketService.deleteTicket(scanner.nextInt());
                break;
                
            case 5:
                ticketService.outputBookings();
                break;
            
            case 6:
                System.out.println("Thank you");
                ticketService.writeToCsv();
                break;

            default:
                System.out.println("Invalid input");
                break;
        }
        } while (id != 6);
        
    }
    
}
