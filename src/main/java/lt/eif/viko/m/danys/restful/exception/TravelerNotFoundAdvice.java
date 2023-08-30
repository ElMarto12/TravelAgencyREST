package lt.eif.viko.m.danys.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TravelerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TravelerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String travelerNotFoundHandler(TravelerNotFoundException ex){
        return ex.getMessage();
    }
}
