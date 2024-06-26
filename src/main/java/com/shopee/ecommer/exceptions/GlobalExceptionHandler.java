package com.shopee.ecommer.exceptions;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.models.responses.ErrorResponse;
import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//Declared bean and scan all controller
//Return Error for user
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public String handleFeignStatusException(FeignException e, HttpServletResponse response) {
        if(e.status() == HttpStatus.BAD_REQUEST.value()){
            throw new UnauthorizedRequestException(MessageErrors.ACCOUNT_USERNAME_OR_PASS_INVALID);
        }
        return "feignError";
    }

    //Throw common Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.debug(ex.getLocalizedMessage());
        ex.printStackTrace();
        return commonHandlerException(Strings.EMPTY, "Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleBadRequest(Exception ex) {
        return commonHandlerException(ex.getLocalizedMessage(), "Bad Request", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex) {
        return commonHandlerException(ex.getLocalizedMessage(), "Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedRequestException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleUnauthorizedRequestException(UnauthorizedRequestException ex) {
        return commonHandlerException(ex.getLocalizedMessage(), "Unauthorized Request", HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> commonHandlerException(String exMessage, String message, HttpStatus httpStatus) {
        List<String> details = new ArrayList<>();
        //Save Message in Here
        details.add(exMessage);
        ErrorResponse error = new ErrorResponse(message, details);
        return new ResponseEntity(error, httpStatus);
    }

}