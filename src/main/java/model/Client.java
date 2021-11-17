package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder

public class Client {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;
    private final String country;

    public  Client(int id, String firstName, String lastName, String email, String gender, String country){
     this.id=id;
     this.firstName =firstName;
     this.lastName =lastName;
     this.email=email;
     this.gender=gender;
     this.country=country;
    }

    @Override
    public String toString() {
        return '\n' + "Client{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
