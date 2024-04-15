package com.giordan.apigateway.mapper;

import com.giordan.apigateway.model.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonMapper<T> {
    Person toEntity(T dto);

    T toDto(Person entity);

}
