package ua.lviv.iot.imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.imdb.domain.Nomination;
import ua.lviv.iot.imdb.dto.NominationDto;
import ua.lviv.iot.imdb.dto.assembler.NominationDtoAssembler;
import ua.lviv.iot.imdb.service.NominationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nominations")
public class NominationController {
    @Autowired
    private NominationService nominationService;
    @Autowired
    private NominationDtoAssembler nominationDtoAssembler;

    @GetMapping(value = "/{nominationId}")
    public ResponseEntity<NominationDto> getNomination(@PathVariable Integer nominationId) {
        Nomination nomination = nominationService.findById(nominationId);
        NominationDto nominationDto = nominationDtoAssembler.toModel(nomination);
        return new ResponseEntity<>(nominationDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<NominationDto>> getAllNominations() {
        List<Nomination> nominations = nominationService.findAll();
        CollectionModel<NominationDto> nominationDtos = nominationDtoAssembler.toCollectionModel(nominations);
        return new ResponseEntity<>(nominationDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<NominationDto> addNomination(@RequestBody Nomination nomination) {
        Nomination newNomination = nominationService.create(nomination);
        NominationDto nominationDto = nominationDtoAssembler.toModel(newNomination);
        return new ResponseEntity<>(nominationDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{nominationId}")
    public ResponseEntity<?> updateNomination(@RequestBody Nomination uNomination, @PathVariable Integer nominationId) {
        nominationService.update(nominationId, uNomination);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{nominationId}")
    public ResponseEntity<?> deleteNomination(@PathVariable Integer nominationId) {
        nominationService.delete(nominationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
