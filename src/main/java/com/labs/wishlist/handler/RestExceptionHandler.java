//package com.labs.wishlist.handler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice
//public class RestExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
//
//        request.getDescription(false);
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
//    }
//}
