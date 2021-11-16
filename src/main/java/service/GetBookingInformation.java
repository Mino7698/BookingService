package service;

import model.Apartment;
import model.Client;
import util.ConnectingToMyDatabase;

import java.sql.*;
import java.util.ArrayList;

public class GetBookingInformation {

    private GetBookingInformation() {
        throw new IllegalStateException("Utility class");
    }


    public static ArrayList<Apartment> getAllApartments() throws SQLException {
        ConnectingToMyDatabase connectingToMyDatabase = ConnectingToMyDatabase.getInstance();
        final Connection connection = connectingToMyDatabase.getConnection();
        final ArrayList<Apartment> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet apartmentRS = stmt.executeQuery("SELECT * FROM apartment");
            while (apartmentRS.next()) {
                result.add(new Apartment(apartmentRS.getInt(1), apartmentRS.getString(2),
                        apartmentRS.getString(3), apartmentRS.getString(4),
                        apartmentRS.getInt(5), apartmentRS.getInt(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    public static ArrayList<Client> getAllClients() throws SQLException {
        ConnectingToMyDatabase connectingToMyDatabase = ConnectingToMyDatabase.getInstance();
        final Connection connection = connectingToMyDatabase.getConnection();
        final ArrayList<Client> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet clientRS = stmt.executeQuery("SELECT * FROM client");
            while (clientRS.next()) {
                result.add(new Client(clientRS.getInt(1), clientRS.getString(2),
                        clientRS.getString(3), clientRS.getString(4),
                        clientRS.getString(5), clientRS.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    public static ArrayList<Apartment> availableApartmentsForTheSelectedDate(String date) throws SQLException {
        ConnectingToMyDatabase connectingToMyDatabase = ConnectingToMyDatabase.getInstance();
        final Connection connection = connectingToMyDatabase.getConnection();
        final ArrayList<Apartment> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet bookingRS = stmt.executeQuery("select * from booking where start_date_of_booking < '" + date + "' and finish_date_of_booking > '" + date + "'");
            ArrayList<Integer> bookedApartments = new ArrayList<>();
            while (bookingRS.next()) {
                bookedApartments.add(bookingRS.getInt(3));
            }
            ResultSet apartmentRS = stmt.executeQuery("SELECT * FROM apartment");
            MARK:
            while (apartmentRS.next()) {
                for (Integer bookedApartment : bookedApartments) {
                    if (((Integer) apartmentRS.getInt(1)).equals(bookedApartment))
                        continue MARK;
                }
                result.add(new Apartment(apartmentRS.getInt(1), apartmentRS.getString(2),
                        apartmentRS.getString(3), apartmentRS.getString(4),
                        apartmentRS.getInt(5), apartmentRS.getInt(6)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return result;

    }

    public static ArrayList<Apartment> customerOrdersWithSelectedId(int clientID) throws SQLException {
        ConnectingToMyDatabase connectingToMyDatabase = ConnectingToMyDatabase.getInstance();
        final Connection connection = connectingToMyDatabase.getConnection();
        final ArrayList<Apartment> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet bookingRS = stmt.executeQuery("SELECT * FROM booking inner join apartment " +
                    " on booking.apartment_id = apartment.id where client_id =" + clientID);
            while (bookingRS.next()) {
                result.add(new Apartment(bookingRS.getInt(3), bookingRS.getString(7),
                        bookingRS.getString(8), bookingRS.getString(9),
                        bookingRS.getInt(10), bookingRS.getInt(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

}
