package nl.fontys.s3.comfyshop.bussiness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserException extends ResponseStatusException {
    public InvalidUserException(String errorId) {
        super(HttpStatus.BAD_REQUEST, errorId);
    }

}
