package com.giordan.apigateway.services;

import com.giordan.apigateway.dtos.PersonDto;
import com.giordan.apigateway.dtos.v2.PersonDtoV2;
import com.giordan.apigateway.mapper.PersonMapper;
import com.giordan.apigateway.model.Person;
import com.giordan.apigateway.repositories.PersonRepository;
import com.giordan.apigateway.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper<PersonDtoV2> personMapperV2;
    private final PersonMapper<PersonDto> personMapperV1;
    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    public PersonDto findPersonById(Long id) {
        logger.info("Finding person by id: " + id);
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            logger.warning("Person not found");
            throw new ObjectNotFoundException(id, "Person");
        }
        return personMapperV1.toDto(person);
    }

    public List<PersonDto> findAll() {
        logger.info("Finding all persons");
        List<Person> list = personRepository.findAll();
        if (list.isEmpty()) {
            logger.warning("No persons found");
            throw new ObjectNotFoundException("Person");
        }
        return list.stream()
                .map(personMapperV1::toDto)
                .toList();
    }

    public PersonDto addPerson(PersonDto personDto) {
        logger.info("Adding person: " + personDto);
        return personMapperV1.toDto(personRepository.save(personMapperV1.toEntity(personDto)));
    }
    public PersonDtoV2 addPersonV2(PersonDtoV2 personDtoV2) {
        logger.info("Adding person: " + personDtoV2);
        return personMapperV2.toDto(personRepository.save(personMapperV2.toEntity(personDtoV2)));
    }

}
