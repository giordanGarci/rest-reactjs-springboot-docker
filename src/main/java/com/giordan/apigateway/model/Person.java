package com.giordan.apigateway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity @Table(name = "person")
public class Person extends RepresentationModel<Person> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long key;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
}
