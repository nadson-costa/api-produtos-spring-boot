package com.nadson.api_produtos.exception;

import com.nadson.api_produtos.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request
    ){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                "Not found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        //Observação: request.getDescription() retorna "uri=/produtos/999. O .replace() é justamente para deixar só o /produtos/999

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericError(
            Exception ex,
            WebRequest request
    ){
        ErrorResponse errorResponse = new ErrorResponse(
                500,
                "Internal server erro",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
