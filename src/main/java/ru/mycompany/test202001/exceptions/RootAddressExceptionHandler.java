package ru.mycompany.test202001.exceptions;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mycompany.test202001.controllers.TaxController;

/**
 * @author Eugene Chernov
 */
@ControllerAdvice(assignableTypes = TaxController.class)
public class RootAddressExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected ResponseEntity<RequestIllegalParamException> handleInvalidDataAccessApiUsageException() {
        String message = "Required parameter 'row' and optional parameter 'col' " +
                "can have the following values a, b, c, d, y.";
        return new ResponseEntity<>(new RequestIllegalParamException(message),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

class RequestIllegalParamException {
    private String message;

    public RequestIllegalParamException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
