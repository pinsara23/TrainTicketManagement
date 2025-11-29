/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.trm.main;

import com.trm.dto.BookingDto;
import com.trm.service.TicketService;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class TrainTicketManagement {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        TicketService ticketService = new TicketService();
        int id = 0;
        System.out.println("---------------------------------------------------------");
        System.out.println("Welcome to Ticket manager");
        System.out.println("---------------------------------------------------------");
        do {            
            System.out.println("Enter the option id \n 1.See Train timetable \n 2. Book a ticket \n 3. Edit Ticket details \n 4. Delete booked ticket"
                + "\n 5. See ticket Details \n 6.Exit");
        
        id = scanner.nextInt();
        
        switch (id) {
            case 1:
                ticketService.outputTrains();
                break;
                
            case 2:
                BookingDto bookDto = new BookingDto();
                System.out.println("Enter book id");
                bookDto.setBookingId(scanner.nextInt());
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
                
                
                boolean result  = ticketService.bookTicket(bookDto);
                if (result) {
                    System.out.println("Your booking id "+bookDto.getBookingId());
                }else{
                    System.out.println("Error in book ticket");
                }
                break;
            
            case 3:
                
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
            default:
                System.out.println("Invalid input");
        }
        } while (id != 6);
        
    }
    
}
