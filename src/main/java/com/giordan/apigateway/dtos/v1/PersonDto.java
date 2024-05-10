package com.giordan.apigateway.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "firstName", "lastName", "age", "gender"})
public record PersonDto(
        @JsonProperty(value = "id") Long key, String firstName, String lastName, Integer age, String gender) {
}
