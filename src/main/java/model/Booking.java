package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@Builder

public class Booking {
    private final int id;
    private final int clientId;
    private final int apartmentId;
    private final String startDateOfBooking;
    private final String finishDateOfBooking;

    public Booking(int id, int clientId, int apartmentId, String startDateOfBooking, String finishDateOfBooking) {
        this.id = id;
        this.clientId = clientId;
        this.apartmentId = apartmentId;
        this.startDateOfBooking = startDateOfBooking;
        this.finishDateOfBooking = finishDateOfBooking;
    }

    @Override
    public String toString() {
        return '\n' + "Booking{" +
                "id=" + id +
                ", client_id=" + clientId +
                ", apartment_id=" + apartmentId +
                ", start_date_of_booking='" + startDateOfBooking + '\'' +
                ", finish_date_of_booking='" + finishDateOfBooking + '\'' +
                '}';
    }

}
