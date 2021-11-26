package service;

import model.Apartment;
import model.Client;
import model.UserPassURL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingRepository {

    private static Connection con;
    public static Connection getConnection(){
        try {
            con = DriverManager.getConnection(UserPassURL.URL1.getValue(), UserPassURL.USER1.getValue(), UserPassURL.PASSWORD1.getValue());
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
        return con;
    }

    public void createBooking(int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking){
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
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
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }

    }

    public List<Apartment> getAllApartments(){
        List<Apartment> result = new ArrayList<>();
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet apartmentRS = stmt.executeQuery("SELECT * FROM apartment");
            result = parsingOfApartments(apartmentRS);
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
        return result;
    }

    public List<Client> getAllClients(){
        List<Client> result = new ArrayList<>();
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet clientRS = stmt.executeQuery("SELECT * FROM client");
            result = parsingOfClients(clientRS);
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
        return result;
    }

    public List<Apartment> getAvailableApartmentsForTheSelectedDate(LocalDate date){
        List<Apartment> result = new ArrayList<>();
        String sql = "select apartment.id, apartment.country, apartment.city, apartment.street_adress, apartment.apartment_number, apartment.price from apartment left join " +
                "booking on booking.apartment_id = apartment.id where start_date_of_booking > (?) or finish_date_of_booking < (?) order by apartment_id asc";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf( date ));
            stmt.setDate(2, java.sql.Date.valueOf( date ));
            ResultSet bookingRS = stmt.executeQuery();
            result = parsingOfApartments(bookingRS);
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
        return result;
    }

    public List<Apartment> getCustomersOrdersWithSelectedId(int clientID){
        List<Apartment> result = new ArrayList<>();
        String sql = "SELECT apartment.id, apartment.country, apartment.city, apartment.street_adress, apartment.apartment_number, apartment.price FROM " +
                "apartment inner join booking on booking.apartment_id = apartment.id where client_id = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,clientID);
            ResultSet bookingRS = stmt.executeQuery();
            result = parsingOfApartments(bookingRS);
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
        return result;
    }

    public List<Apartment> parsingOfApartments (ResultSet resultSet,
                                                int  id,
                                                int country,
                                                int city,
                                                int streetAdress,
                                                int apartmentNumber ,
                                                int price) throws SQLException {
        final List<Apartment> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Apartment(resultSet.getInt(id), resultSet.getString(country),
                    resultSet.getString(city), resultSet.getString(streetAdress),
                    resultSet.getInt(apartmentNumber), resultSet.getInt(price)));
        }
        return result;
    }

    public List<Apartment> parsingOfApartments (ResultSet resultSet) throws SQLException {
        final List<Apartment> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Apartment(resultSet.getInt("id"),
                    resultSet.getString("country"),
                    resultSet.getString("city"),
                    resultSet.getString("street_adress"),
                    resultSet.getInt("apartment_number"),
                    resultSet.getInt("price")));
        }
        return result;
    }

    public List<Client> parsingOfClients (ResultSet resultSet,
                                          int  id,
                                          int firstName,
                                          int lastName,
                                          int email,
                                          int gender,
                                          int country) throws SQLException {
        final List<Client> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Client(resultSet.getInt(id), resultSet.getString(firstName),
                    resultSet.getString(lastName), resultSet.getString(email),
                    resultSet.getString(gender), resultSet.getString(country)));
        }
        return result;
    }

    public List<Client> parsingOfClients (ResultSet resultSet) throws SQLException {
        final List<Client> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Client(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("gender"),
                    resultSet.getString("country")));
        }
        return result;
    }

}
