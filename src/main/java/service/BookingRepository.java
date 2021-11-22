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

public class BookingRepository {



    public static void setBooking(int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking){
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

    public static List<Apartment> parsingOfApartments (ResultSet resultSet, int  id, int country, int city, int streetAdress, int apartmentNumber , int price) throws SQLException {
        final List<Apartment> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Apartment(resultSet.getInt(id), resultSet.getString(country),
                    resultSet.getString(city), resultSet.getString(streetAdress),
                    resultSet.getInt(apartmentNumber), resultSet.getInt(price)));
        }
        return result;
    }

    public static List<Apartment> parsingOfApartments (ResultSet resultSet) throws SQLException {
        final List<Apartment> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Apartment(resultSet.getInt("id"), resultSet.getString("country"),
                    resultSet.getString("city"), resultSet.getString("street_adress"),
                    resultSet.getInt("apartment_number"), resultSet.getInt("price")));
        }
        return result;
    }

    public static List<Client> parsingOfClients (ResultSet resultSet, int  id, int firstName, int lastName, int email, int gender, int country) throws SQLException {
        final List<Client> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Client(resultSet.getInt(id), resultSet.getString(firstName),
                    resultSet.getString(lastName), resultSet.getString(email),
                    resultSet.getString(gender), resultSet.getString(country)));
        }
        return result;
    }

    public static List<Client> parsingOfClients (ResultSet resultSet) throws SQLException {
        final List<Client> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Client(resultSet.getInt("id"), resultSet.getString("first_name"),
                    resultSet.getString("last_name"), resultSet.getString("email"),
                    resultSet.getString("gender"), resultSet.getString("country")));
        }
        return result;
    }

}
