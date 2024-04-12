package com.medicalstore.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @SalesNotFoundException
 * @Author Siva Ganesh
 */
//it returns statuscode
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SalesNotFoundException extends RuntimeException {

    public SalesNotFoundException(String message) {
        super(message);
    }
}
