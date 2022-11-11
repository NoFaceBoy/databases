package ua.lviv.iot.imdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Award {
    private Integer id;
    private Integer movieId;
    private String name;
    private Integer year;
}
