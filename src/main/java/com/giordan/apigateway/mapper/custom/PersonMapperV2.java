package com.giordan.apigateway.mapper.custom;

import com.giordan.apigateway.dtos.v2.PersonDtoV2;
import com.giordan.apigateway.mapper.PersonMapper;
import com.giordan.apigateway.model.Person;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class PersonMapperV2 implements PersonMapper<PersonDtoV2> {

    @Override
    public Person toEntity(PersonDtoV2 dto) {
        return new Person(null, dto.firstName(), dto.lastName(), dto.age(), dto.gender());
    }

    @Override
    public PersonDtoV2 toDto(Person entity) {
        return new PersonDtoV2(entity.getKey(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getGender(), LocalDate.now());
    }
}