package com.giordan.apigateway.services;

import com.giordan.apigateway.model.Person;
import com.giordan.apigateway.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());
    public Person findPersonById(Long id) {
        logger.info("Finding person by id: " + id);
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            logger.warning("Person not found");
            throw new ObjectNotFoundException(id, Person.class.getName());
        }
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all persons");
        List<Person> list = personRepository.findAll();
        if (list.isEmpty()) {
            logger.warning("No persons found");
            throw new ObjectNotFoundException(Optional.of("No persons found"), Person.class.getName());
        }
        return list;
    }

    public Person addPerson(Person person) {
        logger.info("Adding person: " + person);
        return personRepository.save(person);
    }
}
