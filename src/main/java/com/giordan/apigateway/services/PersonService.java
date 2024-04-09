package com.giordan.apigateway.services;

import com.giordan.apigateway.dtos.PersonDto;
import com.giordan.apigateway.model.Person;
import com.giordan.apigateway.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    public PersonDto findPersonById(Long id) {
        logger.info("Finding person by id: " + id);
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            logger.warning("Person not found");
            throw new ObjectNotFoundException(id, Person.class.getName());
        }
        return objToDto(person);
    }

    public List<PersonDto> findAll() {
        logger.info("Finding all persons");
        List<Person> list = personRepository.findAll();
        if (list.isEmpty()) {
            logger.warning("No persons found");
            throw new ObjectNotFoundException(Optional.empty(), "No persons found for findAll");
        }
        return list.stream()
                .map(this::objToDto)
                .toList();
    }

    public PersonDto addPerson(Person person) {
        logger.info("Adding person: " + person);
        return objToDto(personRepository.save(person));
    }

    private PersonDto objToDto(Person person) {
        return new PersonDto(person.getFirstName(), person.getLastName(), person.getAge(), person.getGender());
    }
}
