package ua.lviv.iot.imdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "movie", collectionRelation = "movies")
public class MovieDto extends RepresentationModel<MovieDto> {
    private final Integer id;
    private final String name;
    private final String description;
    private final Integer budget;
    private final Integer year;
    private final String facts;
}
