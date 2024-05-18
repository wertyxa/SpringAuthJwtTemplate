package com.wertyxa.springauthjwttemplate.core.utils.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ResponseException extends ResponseStatusException {
    private final String internalCode;
    public ResponseException(HttpStatusCode status, String reason, String internalCode) {
        super(status, reason);
        this.internalCode = internalCode;
    }
}
