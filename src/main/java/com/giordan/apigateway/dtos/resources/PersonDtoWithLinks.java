package com.giordan.apigateway.dtos.resources;
import com.giordan.apigateway.dtos.v1.PersonDto;
import com.giordan.apigateway.dtos.v2.PersonDtoV2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoWithLinks extends RepresentationModel<PersonDtoWithLinks> {
    private PersonDto personDto;
}