package com.giordan.apigateway.dtos.v2;

import java.time.LocalDate;

public record PersonDtoV2(String firstName, String lastName, Integer age, String gender, LocalDate birthDate) {
}
