package com.giordan.apigateway.controllers;

import com.giordan.apigateway.dtos.v1.PersonDto;
import com.giordan.apigateway.dtos.v2.PersonDtoV2;
import com.giordan.apigateway.services.PersonService;
import com.giordan.apigateway.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Tag(name = "Person", description = "Endpoints for managing people.")
public class PersonController {

    private final PersonService personServices;

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a person", description = "Find a person by its id",
            tags = {"Person"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(responseCode = "204", description = "No content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public ResponseEntity<?> findPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personServices.findPersonById(id));
    }

    @GetMapping(value = "/all",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "List all people", description = "List all people in the database.",
            tags = {"Person"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
                                )
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(personServices.findAll());
    }

    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a person", description = "Add a person to the database by passing in a JSON,XML or YAML representation of the person.",
            tags = {"Person"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
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
