import model.Apartment;
import org.junit.*;
import service.BookingRepository;
import service.BookingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class AutoTestsOfBookingService {
    static BookingRepository bookingRepository = new BookingRepository();
    static BookingService BookingInformation = new BookingService(bookingRepository);
    static List<Apartment> testListOfGettingCustomerOrdersWithSelectedId = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() throws IOException {

        List<String> strCreateTables = Files.readAllLines(Paths.get("src/main/resources/CreateTables.sql"));
        String sqlCreateTables = String.join("", strCreateTables);

        List<String> strCreateRows = Files.readAllLines(Paths.get("src/main/resources/CreateRows.sql"));
        String sqlCreateRows = String.join("", strCreateRows);

        try (Connection connection = BookingRepository.getConnection(); Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sqlCreateTables);
            stmt.executeUpdate(sqlCreateRows);
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    @AfterClass
    public static void afterClass() throws IOException {
        List<String> strDropTables = Files.readAllLines(Paths.get("src/main/resources/DropTables.sql"));
        String sqlDropTables = String.join("", strDropTables);
        try (Connection connection = BookingRepository.getConnection(); Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sqlDropTables);
        } catch (SQLException ex) {
            Logger.getLogger(BookingRepository.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    @Test
    public void testOfGettingNumberOfCustomersOrdersWithSelectedId1() throws SQLException {
        Assert.assertEquals(3, BookingInformation.getCustomersOrdersWithSelectedId(1).size());
    }

    @Test
    public void testOfGettingNumberOfCustomersOrdersWithSelectedId2() throws SQLException {
        Assert.assertEquals(2, BookingInformation.getCustomersOrdersWithSelectedId(2).size());
    }

    @Test
    public void testOfGettingNumberOfCustomersOrdersWithSelectedId3() throws SQLException {
        Assert.assertEquals(1, BookingInformation.getCustomersOrdersWithSelectedId(3).size());
    }

    @Test
    public void testOfGettingNumberApartments() throws SQLException {
        Assert.assertEquals(10, BookingInformation.getAllApartments().size());
    }

    @Test
    public void testOfGettingNumberClients() throws SQLException {
        Assert.assertEquals(10, BookingInformation.getAllClients().size());
    }

    @Test
    public void testOfGettingNumberOfAvailableApartmentsForTheSelectedDate01_07_2001() throws SQLException {
        Assert.assertEquals(4, BookingInformation.getAvailableApartmentsForTheSelectedDate(LocalDate.of(2001,7,1)).size());
    }
    @Test
    public void testOfGettingNumberOfAvailableApartmentsForTheSelectedDate01_07_2007() throws SQLException {
        Assert.assertEquals(5, BookingInformation.getAvailableApartmentsForTheSelectedDate(LocalDate.of(2007,7,1)).size());
    }
    @Test
    public void testOfGettingNumberOfAvailableApartmentsForTheSelectedDate01_07_2009() throws SQLException {
        Assert.assertEquals(5, BookingInformation.getAvailableApartmentsForTheSelectedDate(LocalDate.of(2009,7,1)).size());
    }
    @Test
    public void testOfGettingNumberOfAvailableApartmentsForTheSelectedDate01_07_2100() throws SQLException {
        Assert.assertEquals(6, BookingInformation.getAvailableApartmentsForTheSelectedDate(LocalDate.of(2100,7,1)).size());
    }

    @Test
    public void testOfGettingCustomersOrdersWithSelectedId() throws SQLException {
        Apartment apartmentId4 =   Apartment.builder()
                .id(4)
                .country("Portugal")
                .city("S??o Pedro do Estoril")
                .streetAdress("8 Springview Hill")
                .apartmentNumber(48)
                .price(2030)
                .build();
        Apartment apartmentId6 =   Apartment.builder()
                .id(6)
                .country("Bangladesh")
                .city("Mirz??pur")
                .streetAdress("9067 Eastwood Street")
                .apartmentNumber(77)
                .price(300)
                .build();
        Assert.assertEquals(asList(apartmentId4,apartmentId6), BookingInformation.getCustomersOrdersWithSelectedId(2));
    }

    @Test
    public void testOfCreatingBooking() throws SQLException {
        BookingInformation.createBooking(10, 5, "07-01-2015", "02-06-2016");
    }

}
