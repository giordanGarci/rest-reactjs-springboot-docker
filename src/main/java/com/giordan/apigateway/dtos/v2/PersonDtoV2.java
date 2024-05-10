package com.giordan.apigateway.dtos.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({"id", "firstName", "lastName", "age", "gender", "birthDate"})
public record PersonDtoV2(
        @JsonProperty(value = "id") Long key, String firstName, String lastName, Integer age, String gender, LocalDate birthDate) {}
