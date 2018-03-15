package com.kakaopay.coupon.error;

import com.kakaopay.coupon.error.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ErrorController {

    public static final String NULL_BODY = "null.body";
    public static final String ARG_TYPE_MISMATCH = "argument.type.mismatch";
    public static final String NOT_JSON_MEDIA_TYPE = "not.json";
    public static final String INVALID_PAGINATION = "invalid.pagination";

    /*
        Custom Exception
     */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistCouponException.class)
    @ResponseBody
    public ErrorInfo handleNotExistCoupon(HttpServletRequest req, NotExistCouponException ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex.getLocalizedMessage(), NotExistCouponException.errorCode);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyEmailException.class)
    @ResponseBody
    public ErrorInfo handleEmptyEmail(HttpServletRequest req, EmptyEmailException ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex.getLocalizedMessage(), EmptyEmailException.errorCode);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseBody
    public ErrorInfo handleInvalidEmail(HttpServletRequest req, InvalidEmailException ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex.getLocalizedMessage(), InvalidEmailException.errorCode);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseBody
    public ErrorInfo handleDuplicateEmail(HttpServletRequest req, DuplicateEmailException ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex.getLocalizedMessage(), DuplicateEmailException.errorCode);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({EmptyCodeException.class, CodeCollisionException.class})
    @ResponseBody
    public ErrorInfo handleFailToGenerateCode(HttpServletRequest req, Exception ex) {
        String errorCode = (ex instanceof EmptyCodeException) ?
                EmptyCodeException.errorCode : CodeCollisionException.errorCode;
        return new ErrorInfo(req.getRequestURL().toString(), ex.getLocalizedMessage(), errorCode);
    }

    /*
        Spring Exception
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorInfo handleNullRequestBody(HttpServletRequest req, Exception ex) {
        log.info("handleNullRequestBody - Required request body is null");
        return new ErrorInfo(req.getRequestURL().toString(), "Required request body is null", NULL_BODY);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ErrorInfo handleArgumentTypeMismatch(HttpServletRequest req, Exception ex) {
        log.info("handleArgumentTypeMismatch - Argument type mismatch");
        return new ErrorInfo(req.getRequestURL().toString(), "Argument type mismatch", ARG_TYPE_MISMATCH);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ErrorInfo handleNotJsonRequest(HttpServletRequest req, Exception ex) {
        log.info("handleNotJsonRequest - Only support Content type 'application/json'");
        return new ErrorInfo(req.getRequestURL().toString(), "Only support Content type 'application/json'", NOT_JSON_MEDIA_TYPE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseBody
    public ErrorInfo handleInvalidPagination(HttpServletRequest req, PropertyReferenceException ex) {
        String uri = req.getRequestURL().toString();
        if(uri.contains("/api/v1/coupon")) {
            log.info("handleInvalidPagination - Pagination param is invalid");
            return new ErrorInfo(uri, "Pagination param is invalid", INVALID_PAGINATION);
        } else {
            throw ex;
        }
    }



}
