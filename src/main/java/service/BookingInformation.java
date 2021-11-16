package service;

import model.Apartment;
import model.Client;
import util.ConnectingToMyDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingInformation {

    public BookingInformation() {
    }


    public void setBooking(int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking) throws SQLException {
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("do $$\n" +
                    "begin\n" +
                    "IF NOT EXISTS (\n" +
                    "   SELECT FROM booking\n" +
                    "   WHERE (apartment_id = " + apartmentId + " " +
                    "   and ((start_date_of_booking <= '" + startDateOfBooking + "' and finish_date_of_booking >= '" + startDateOfBooking + "')" +
                    "   or (start_date_of_booking <= '" + finishDateOfBooking + "' and finish_date_of_booking >= '" + finishDateOfBooking + "')))\n" +
                    ") \n" +
                    "THEN\n" +
                    "insert into booking (client_id, apartment_id, start_date_of_booking, finish_date_of_booking) " +
                    "values (" + clientId + "," + apartmentId + ",'" + startDateOfBooking + "','" + finishDateOfBooking + "');\n" +
                    "END IF;\n" +
                    "end;\n" +
                    "$$;");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Apartment> getAllApartments() throws SQLException {
        List<Apartment> result = new ArrayList<>();
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet apartmentRS = stmt.executeQuery("SELECT * FROM apartment");
            result = ParsingUtil.parsingOfApartments(apartmentRS,1,2,3,4,5,6);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet clientRS = stmt.executeQuery("SELECT * FROM client");
            result = ParsingUtil.parsingOfClients(clientRS,1,2,3,4,5,6);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Apartment> availableApartmentsForTheSelectedDate(String date) throws SQLException {
        List<Apartment> result = new ArrayList<>();
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet bookingRS = stmt.executeQuery("select * from apartment left join booking on booking.apartment_id = apartment.id where start_date_of_booking > '" + date + "' or finish_date_of_booking < '" + date + "'" +
                    "order by apartment_id asc;");
            result = ParsingUtil.parsingOfApartments(bookingRS,1,2,3,4,5,6);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Apartment> customerOrdersWithSelectedId(int clientID) throws SQLException {
        List<Apartment> result = new ArrayList<>();
        try (Connection connection = ConnectingToMyDatabase.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet bookingRS = stmt.executeQuery("SELECT * FROM booking inner join apartment " +
                    " on booking.apartment_id = apartment.id where client_id =" + clientID);
            result = ParsingUtil.parsingOfApartments(bookingRS,3,7,8,9,10,11);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
