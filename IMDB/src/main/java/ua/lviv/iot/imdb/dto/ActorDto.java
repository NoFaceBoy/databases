package ua.lviv.iot.imdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "actor", collectionRelation = "actors")
public class ActorDto extends RepresentationModel<ActorDto> {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final Date birthdate;
}
