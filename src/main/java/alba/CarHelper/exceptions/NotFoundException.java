package alba.CarHelper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
//public class NotFoundException extends RuntimeException {
//}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such messages")
public class NotFoundException extends RuntimeException {
}
