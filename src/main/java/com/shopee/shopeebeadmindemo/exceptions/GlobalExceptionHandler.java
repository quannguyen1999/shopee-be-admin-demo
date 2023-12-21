package com.shopee.shopeebeadmindemo.exceptions;

import com.shopee.shopeebeadmindemo.controllers.rest.AccountRestController;
import com.shopee.shopeebeadmindemo.models.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
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
@ControllerAdvice(basePackageClasses = {AccountRestController.class})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Throw common Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.info(ex.getMessage());
        return commonHandlerException(ex.getLocalizedMessage(), "Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
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

    private ResponseEntity<Object> commonHandlerException(String exMessage, String message, HttpStatus httpStatus) {
        List<String> details = new ArrayList<>();
        //Save Message in Here
        details.add(exMessage);
        ErrorResponse error = new ErrorResponse(message, details);
        return new ResponseEntity(error, httpStatus);
    }

//    //phải khai báo @Vallidated trong các model thì mới hoạt động
//    //sau đó nếu có lỗi thì sẽ báo cho client biết
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        //khởi tạo
//        List<String> details = new ArrayList<>();
//
//        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
//
//            details.add(error.getDefaultMessage());
//
//        }
//
//        ErrorResponse error = new ErrorResponse("Validation Failed", details);
//
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }
}