package com.gabrielluciano.api.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String id) {
        super(String.format("Object with id '%s' not found", id));
    }
}
