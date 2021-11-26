package service;

import model.Apartment;
import model.Client;

import java.time.LocalDate;
import java.util.List;

public class BookingService {

    BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void createBooking(int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking){
       bookingRepository.createBooking(clientId,apartmentId, startDateOfBooking, finishDateOfBooking);
    }

    public List<Apartment> getAllApartments(){
        return bookingRepository.getAllApartments();
    }

    public List<Client> getAllClients(){
        return bookingRepository.getAllClients();
    }

    public List<Apartment> getAvailableApartmentsForTheSelectedDate(LocalDate date){
        return bookingRepository.getAvailableApartmentsForTheSelectedDate(date);
    }

    public List<Apartment> getCustomersOrdersWithSelectedId(int clientID){
        return bookingRepository.getCustomersOrdersWithSelectedId(clientID);
    }

}
