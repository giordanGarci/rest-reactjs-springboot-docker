package com.giordan.apigateway.services.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found")
public class ObjectNotFoundException extends EntityNotFoundException {
    public ObjectNotFoundException(Object id, String entityName) {
        super("Object not found with id: " + id + " for entity: " + entityName);
    }

    public ObjectNotFoundException(String entityName) {
        super("Any objects found for entity: " + entityName);
    }
}
