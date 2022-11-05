package ua.lviv.iot.imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.imdb.domain.Director;
import ua.lviv.iot.imdb.dto.DirectorDto;
import ua.lviv.iot.imdb.dto.assembler.DirectorDtoAssembler;
import ua.lviv.iot.imdb.service.DirectorService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/directors")
public class DirectorController {
    @Autowired
    private DirectorService directorService;
    @Autowired
    private DirectorDtoAssembler directorDtoAssembler;

    @GetMapping(value = "/{directorId}")
    public ResponseEntity<DirectorDto> getDirector(@PathVariable Integer directorId) {
        Director director = directorService.findById(directorId);
        DirectorDto directorDto = directorDtoAssembler.toModel(director);
        return new ResponseEntity<>(directorDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DirectorDto>> getAllDirectors() {
        List<Director> directors = directorService.findAll();
        CollectionModel<DirectorDto> directorDtos = directorDtoAssembler.toCollectionModel(directors);
        return new ResponseEntity<>(directorDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DirectorDto> addDirector(@RequestBody Director director) {
        Director newDirector = directorService.create(director);
        DirectorDto directorDto = directorDtoAssembler.toModel(newDirector);
        return new ResponseEntity<>(directorDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{directorId}")
    public ResponseEntity<?> updateDirector(@RequestBody Director uDirector, @PathVariable Integer directorId) {
        directorService.update(directorId, uDirector);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{directorId}")
    public ResponseEntity<?> deleteDirector(@PathVariable Integer directorId) {
        directorService.delete(directorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
