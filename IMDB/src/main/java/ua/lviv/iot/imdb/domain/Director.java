package ua.lviv.iot.imdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Director {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthdate;
}
