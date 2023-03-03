package nl.fontys.s3.comfyshop.bussiness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NameAlreadyExistsException extends ResponseStatusException {
    public NameAlreadyExistsException (){
        super(HttpStatus.BAD_REQUEST, "NAME_ALREADY_EXISTS");
    }

}
