package alba.CarHelper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such messages")
public class NotFoundException extends RuntimeException {
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(IOException exception) {
        ModelAndView modelAndView = new ModelAndView("/exception/catchedException");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
