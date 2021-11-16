package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode

public class Client {
    private final int id;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String gender;
    private final String country;

    public  Client(int id, String first_name, String last_name, String email, String gender, String country){
     this.id=id;
     this.first_name=first_name;
     this.last_name=last_name;
     this.email=email;
     this.gender=gender;
     this.country=country;
    }

    @Override
    public String toString() {
        return '\n' + "Client{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
