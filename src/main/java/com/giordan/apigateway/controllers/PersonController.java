package com.giordan.apigateway.controllers;

import com.giordan.apigateway.dtos.PersonDto;
import com.giordan.apigateway.dtos.v2.PersonDtoV2;
import com.giordan.apigateway.services.PersonService;
import com.giordan.apigateway.util.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personServices;

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<?> findPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personServices.findPersonById(id));
    }

    @GetMapping(value = "/all",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(personServices.findAll());
    }


     @GetMapping(value = "/v2/all",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<?> findAllV2() {
        return ResponseEntity.ok(personServices.findAll());
    }

    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<?> addPersonV2(@RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personServices.addPerson(personDto));
    }

    @PostMapping(value = "/v2/create",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<?> addPerson(@RequestBody PersonDtoV2 personDtoV2) {
        return ResponseEntity.ok(personServices.addPersonV2(personDtoV2));
    }
}
