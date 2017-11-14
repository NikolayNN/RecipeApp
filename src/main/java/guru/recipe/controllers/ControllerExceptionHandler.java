package guru.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nikolay Horushko
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView numberFormatErrorPage(Exception exception){
        log.error("Handling not Number Format exception");
        log.error(exception.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName("400error");
        return model;
    }
}
