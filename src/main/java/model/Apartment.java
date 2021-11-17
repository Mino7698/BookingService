package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@Builder

public class Apartment {
    private final int id;
    private final String country;
    private final String city;
    private final String streetAdress;
    private final int apartmentNumber;
    private final int price;

    public Apartment(int id, String country, String city, String streetAdress, int apartmentNumber, int price) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.streetAdress = streetAdress;
        this.apartmentNumber = apartmentNumber;
        this.price = price;
    }

    @Override
    public String toString() {
        return '\n' + "Apartment{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street_adress='" + streetAdress + '\'' +
                ", apartment_number=" + apartmentNumber +
                ", price=" + price +
                '}';
    }
}
