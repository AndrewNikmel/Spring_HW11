package Spring_HW.sem11.payment_serv.controller;

import Spring_HW.sem11.payment_serv.exception.ExcessAmountException;
import Spring_HW.sem11.payment_serv.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ExcessAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String excessAmount(ExcessAmountException e){
        return e.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resourceNotFound(ResourceNotFoundException e){
        return e.getMessage();
    }
}
