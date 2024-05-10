package com.giordan.apigateway.services;

import com.giordan.apigateway.controllers.PersonController;
import com.giordan.apigateway.dtos.resources.PersonDtoWithLinksV2;
import com.giordan.apigateway.dtos.v1.PersonDto;
import com.giordan.apigateway.dtos.resources.PersonDtoWithLinks;
import com.giordan.apigateway.dtos.v2.PersonDtoV2;
import com.giordan.apigateway.mapper.PersonMapper;
import com.giordan.apigateway.model.Person;
import com.giordan.apigateway.repositories.PersonRepository;
import com.giordan.apigateway.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper<PersonDtoV2> personMapperV2;
    private final PersonMapper<PersonDto> personMapperV1;
    private static final String ALL_PERSONS = "all-persons";
    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    public PersonDtoWithLinks findPersonById(Long id) {
        logger.info("Finding person by id: " + id);
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            logger.warning("Person not found");
            throw new ObjectNotFoundException(id, "Person");
        }

        PersonDto dto =  personMapperV1.toDto(person);
        PersonDtoWithLinks personDtoWithLinks = new PersonDtoWithLinks(dto);
        return personDtoWithLinks.add(linkTo(methodOn(PersonController.class).findAll()).withRel(ALL_PERSONS));
    }

    public List<PersonDtoWithLinks> findAll() {
        logger.info("Finding all persons");
        List<Person> list = personRepository.findAll();
        if (list.isEmpty()) {
            logger.warning("No persons found");
            throw new ObjectNotFoundException("Person");
        }
        return list.stream()
                .map(person -> {
                    PersonDto dto = personMapperV1.toDto(person);
                    PersonDtoWithLinks dtoWithLinks = new PersonDtoWithLinks(dto);
                    dtoWithLinks.add(linkTo(methodOn(PersonController.class).findPersonById(person.getKey())).withSelfRel());
                    return dtoWithLinks;
                })
                .toList();

    }

    public PersonDtoWithLinks addPerson(PersonDto personDto) {
        logger.info("Adding person: " + personDto);
        Person entity = personMapperV1.toEntity(personDto);
        Person savedPerson = personRepository.save(entity);
        PersonDto savedDto = personMapperV1.toDto(savedPerson);
        PersonDtoWithLinks personDtoWithLinks = new PersonDtoWithLinks(savedDto);
        return personDtoWithLinks.add(linkTo(methodOn(PersonController.class).findAll()).withRel(ALL_PERSONS));
    }
    public PersonDtoWithLinksV2 addPersonV2(PersonDtoV2 personDtoV2) {
        logger.info("Adding person: " + personDtoV2);
        Person entity = personMapperV2.toEntity(personDtoV2);
        Person savedPerson = personRepository.save(entity);
        PersonDtoV2 savedDto = personMapperV2.toDto(savedPerson);
        PersonDtoWithLinksV2 personDtoWithLinks = new PersonDtoWithLinksV2(savedDto);
        return personDtoWithLinks.add(linkTo(methodOn(PersonController.class).findAll()).withRel(ALL_PERSONS));
    }

}
