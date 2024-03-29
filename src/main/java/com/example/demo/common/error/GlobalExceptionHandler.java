package com.example.demo.common.error;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Biz Exception 처리
     * @param e
     * @return
     */
    @ExceptionHandler(value= BizException.class)
    public ResponseEntity<Object> handleBizEx(final BizException e){
        return new ResponseEntity<>(new ErrorResponse(e.errorCode),HttpStatus.OK);
    }

}
