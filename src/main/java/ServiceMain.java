import service.ServiceOfGettingBookingInformation;

public class ServiceMain {
    public static void main(String[] args){
        ServiceOfGettingBookingInformation bookingInformation = new ServiceOfGettingBookingInformation();

        System.out.println(bookingInformation.getAllApartments());
//       System.out.println(bookingInformation.getAllClients());
//        System.out.println(bookingInformation.getAvailableApartmentsForTheSelectedDate("2001-07-01"));
        System.out.println(bookingInformation.getCustomersOrdersWithSelectedId(2));
//        bookingInformation.setBooking(10, 5, "07-01-2015", "02-06-2016");

    }
}
