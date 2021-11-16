package service;

import util.ConnectingToMyDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetBookingInformation {

    private SetBookingInformation() {
        throw new IllegalStateException("Utility class");
    }

    public static void insertBooking(int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking) throws SQLException {
        ConnectingToMyDatabase connectingToMyDatabase = ConnectingToMyDatabase.getInstance();
        final Connection connection = connectingToMyDatabase.getConnection();
        try (Statement stmt = connection.createStatement()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
