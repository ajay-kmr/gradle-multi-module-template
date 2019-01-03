package com.example.commonmodel.controller;

import com.example.commonmodel.dto.ErrorDTO;
import com.example.commonmodel.dto.ResponseDTO;
import com.example.commonmodel.service.MessageHelperServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@CommonsLog
@RestControllerAdvice
public class AppRestControllerAdvice {

    private static final String EMPTY = StringUtils.EMPTY;
    private static final String VALIDATION_ERROR_CODE = "ValidationError";

    private MessageHelperServiceImpl messageSource;

    // Helper Method To Handle Exception -- STARTS--
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO<String>> handleException(HttpMessageNotReadableException exception) {
        log.error("******** Inside HttpMessageNotReadableException Handler ************");
        ResponseDTO<String> responseDTO = new ResponseDTO<String>();
        responseDTO.setStatus(Boolean.FALSE);
        responseDTO.setMessage(messageSource.getMessage("invalid.request", null, "Please provide valid request", LocaleContextHolder.getLocale()));
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(HttpMessageNotReadableException.class.getSimpleName());
        errorDTO.setErrorMessage(ExceptionUtils.getStackTrace(exception));
        responseDTO.setErrors(Collections.singletonList(errorDTO));
        exception.printStackTrace();
        return new ResponseEntity<ResponseDTO<String>>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO<String>> handleException(MethodArgumentNotValidException exception) {
        log.error("******** Inside MethodArgumentNotValidException Handler ************");
        exception.printStackTrace();
        return new ResponseEntity<ResponseDTO<String>>(createValidationError(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    private ResponseDTO<String> createValidationError(MethodArgumentNotValidException exception) {
        return fromBindingErrors(exception.getBindingResult());
    }
    // Helper Method To Handle Exception -- ENDS--

    // Helper Method Error Binding -- STARTS--

    private ResponseDTO<String> fromBindingErrors(Errors errors) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        ResponseDTO<String> errorResponseDTO = new ResponseDTO<String>();
        errorResponseDTO.setStatus(Boolean.FALSE);
        errorResponseDTO.setMessage("Invalid Request. Validation failed. " + errors.getErrorCount() + " error(s)");
        List<ErrorDTO> errorDTOs = errors.getAllErrors()
                .stream()
                .map(objectError -> getErrorDTO(currentLocale, objectError))
                .collect(Collectors.toList());
        errorResponseDTO.setErrors(errorDTOs);
        return errorResponseDTO;
    }

    private ErrorDTO getErrorDTO(Locale currentLocale, ObjectError objectError) {
        String errorMessageOrErrorCode = objectError.getDefaultMessage();
        if (StringUtils.isEmpty(errorMessageOrErrorCode)) {
            errorMessageOrErrorCode = "validation.error";
        }
        return new ErrorDTO(VALIDATION_ERROR_CODE, messageSource.getMessage(errorMessageOrErrorCode, objectError.getArguments(), errorMessageOrErrorCode, currentLocale));
    }
    // Helper Method Error Binding -- ENDS--

    public void setFlashMessageOnStatus(ResponseDTO responseDTO, RedirectAttributes redirectAttributes) {
        redirectAttributes = responseDTO.getStatus() ? redirectAttributes.addFlashAttribute("success", responseDTO.getMessage()) :
                redirectAttributes.addFlashAttribute("error", responseDTO.getMessage());
    }

}
