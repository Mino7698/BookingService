import model.Apartment;
import model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.BookingInformation;

import java.sql.SQLException;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    List<Client> testListOfGettingAllClients = new ArrayList<>();
    List<Apartment> testListOfGettingAllApartments = new ArrayList<>();
    List<Apartment> testListOfGettingAvailableApartmentsForTheSelectedDate = new ArrayList<>();
    List<Apartment> testListOfGettingCustomerOrdersWithSelectedId = new ArrayList<>();

    List<Client> ListOfGettingAllClients = new ArrayList<>();
    List<Apartment> ListOfGettingAllApartments = new ArrayList<>();
    List<Apartment> ListOfGettingAvailableApartmentsForTheSelectedDate = new ArrayList<>();
    List<Apartment> ListOfGettingCustomerOrdersWithSelectedId = new ArrayList<>();


    @Mock
    BookingInformation mockBookingInformation = new BookingInformation();

    @Before
    public void setUp() throws Exception {
    }

    public void creatingListOfApartment() {

        Apartment apartmentId4 = Apartment.builder().id(4).country("Portugal").city("Sao Pedro do Estoril").streetAdress("8 Springview Hill").apartmentNumber(48).price(2030).build();
        Apartment apartmentId6 = Apartment.builder().id(6).country("Bangladesh").city("Mirzapur").streetAdress("9067 Eastwood Street").apartmentNumber(77).price(300).build();
        ListOfGettingCustomerOrdersWithSelectedId.add(apartmentId4);
        ListOfGettingCustomerOrdersWithSelectedId.add(apartmentId6);

    }

    @Test
    public void operationTest() throws SQLException {
        testListOfGettingCustomerOrdersWithSelectedId=ListOfGettingCustomerOrdersWithSelectedId;
        Mockito.when(mockBookingInformation.getCustomerOrdersWithSelectedId(2)).thenReturn(testListOfGettingCustomerOrdersWithSelectedId);

        Assert.assertEquals(testListOfGettingCustomerOrdersWithSelectedId, mockBookingInformation.getCustomerOrdersWithSelectedId(2));

        Mockito.verify(mockBookingInformation).getCustomerOrdersWithSelectedId(2);
        Mockito.verifyNoMoreInteractions(mockBookingInformation);
    }

}
