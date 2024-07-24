package com.nbenliogludev.adservice.exception.handler;

import com.nbenliogludev.adservice.dto.response.RestResponse;
import com.nbenliogludev.adservice.exception.AdNotFoundException;
import com.nbenliogludev.adservice.exception.errormessages.GeneralErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nbenliogludev
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<Object> handleAllExceptions(RuntimeException exception, WebRequest request) {


        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
    }




    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<Object> handleAdNotFoundException(AdNotFoundException exception,
                                                                    WebRequest request) {

        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restResponse);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {

        List<Map<String, String>> errorList = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {

                    Map<String, String> errorMap = new HashMap<>();

                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

                    return errorMap;
                })
                .toList();

        String description = request.getDescription(false);

        GeneralErrorMessage generalErrorMessage = new GeneralErrorMessage(
                LocalDateTime.now(),
                errorList.toString(),
                description
        );

        RestResponse<GeneralErrorMessage> restResponse = RestResponse.error(generalErrorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    private static RestResponse<GeneralErrorMessage> getGeneralErrorMessageRestResponse(RuntimeException exception,
                                                                                        WebRequest request) {

        String message = exception.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessage generalErrorMessages =
                new GeneralErrorMessage(LocalDateTime.now(), message, description);

        return RestResponse.error(generalErrorMessages);
    }
}
