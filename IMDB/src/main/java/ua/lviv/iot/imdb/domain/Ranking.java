package ua.lviv.iot.imdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ranking {
    private Integer id;
    private Integer rating;
    private Integer numberOfUserRates;
    private Integer metascore;
    private Integer numberOfCriticRates;
}
