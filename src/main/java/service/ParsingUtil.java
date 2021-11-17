package service;

import model.Apartment;
import model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParsingUtil {
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
