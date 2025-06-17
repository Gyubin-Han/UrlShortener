package be.gyu.urlShortener.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import be.gyu.urlShortener.exception.ShortUrlNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice(basePackages={"be.gyu.urlShortener.controller"})
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ShortUrlNotFoundException.class)
    public void ShortUrlNotFoundExceptionHandler(HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
