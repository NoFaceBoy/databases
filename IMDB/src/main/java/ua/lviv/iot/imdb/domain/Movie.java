package ua.lviv.iot.imdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    private Integer id;
    private Integer genreId;
    private Integer rankingId;
    private Integer directorsId;
    private String name;
    private String description;
    private Integer budget;
    private Integer year;
    private String facts;
}
