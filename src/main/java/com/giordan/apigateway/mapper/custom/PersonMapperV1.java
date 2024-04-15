package com.giordan.apigateway.mapper.custom;

import com.giordan.apigateway.dtos.PersonDto;
import com.giordan.apigateway.mapper.PersonMapper;
import com.giordan.apigateway.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapperV1 implements PersonMapper<PersonDto> {

    @Override
    public Person toEntity(PersonDto dto) {
        return new Person(null, dto.firstName(), dto.lastName(), dto.age(), dto.gender());
    }

    @Override
    public PersonDto toDto(Person entity) {
        return new PersonDto(entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getGender());
    }
}