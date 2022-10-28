package ua.lviv.iot.imdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Actor {
    private Integer id;
    private Integer rolesId;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer birthdate;
}
