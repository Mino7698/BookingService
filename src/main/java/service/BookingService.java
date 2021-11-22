package service;

import model.Apartment;
import model.Client;
import util.ConnectingToMyDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingService {

    public void setBooking(int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking){
        BookingService bookingInformation = new BookingService();
        bookingInformation.setBooking(clientId,apartmentId, startDateOfBooking, finishDateOfBooking);
    }

    public List<Apartment> getAllApartments(){
        List<Apartment> result = new ArrayList<>();
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet apartmentRS = stmt.executeQuery("SELECT * FROM apartment");
            result = BookingRepository.parsingOfApartments(apartmentRS);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Client> getAllClients(){
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet clientRS = stmt.executeQuery("SELECT * FROM client");
            result = BookingRepository.parsingOfClients(clientRS);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Apartment> getAvailableApartmentsForTheSelectedDate(LocalDate date){
        List<Apartment> result = new ArrayList<>();
        String sql = "select apartment.id, apartment.country, apartment.city, apartment.street_adress, apartment.apartment_number, apartment.price from apartment left join " +
                "booking on booking.apartment_id = apartment.id where start_date_of_booking > (?) or finish_date_of_booking < (?) order by apartment_id asc";
        try (Connection connection = ConnectingToMyDatabase.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf( date ));
            stmt.setDate(2, java.sql.Date.valueOf( date ));
            ResultSet bookingRS = stmt.executeQuery();
            result = BookingRepository.parsingOfApartments(bookingRS);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Apartment> getCustomersOrdersWithSelectedId(int clientID){
        List<Apartment> result = new ArrayList<>();
        String sql = "SELECT apartment.id, apartment.country, apartment.city, apartment.street_adress, apartment.apartment_number, apartment.price FROM " +
                "apartment inner join booking on booking.apartment_id = apartment.id where client_id = ?";
        try (Connection connection = ConnectingToMyDatabase.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,clientID);
            ResultSet bookingRS = stmt.executeQuery();
            result = BookingRepository.parsingOfApartments(bookingRS);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}