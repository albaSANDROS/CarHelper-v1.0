package alba.CarHelper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such messages")
//public class NotFoundException extends RuntimeException {
//}
public class NotFoundException extends RuntimeException {

    public NotFoundException() {

        super("No data found");
    }
}