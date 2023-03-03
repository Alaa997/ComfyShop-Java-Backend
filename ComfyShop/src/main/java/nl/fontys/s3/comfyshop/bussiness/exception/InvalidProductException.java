package nl.fontys.s3.comfyshop.bussiness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidProductException extends ResponseStatusException {
    public InvalidProductException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
