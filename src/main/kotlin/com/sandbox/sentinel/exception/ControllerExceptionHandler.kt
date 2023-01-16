package com.sandbox.sentinel.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest

@RestControllerAdvice
class ControllerExceptionHandler(private val messageSource: MessageSource) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException,
                                               webRequest: ServletWebRequest): ControllerExceptionModel {
        val causes = exception.fieldErrors
            .filter { it.defaultMessage.isNullOrBlank().not() }
            .map { "${it.field}: ${it.defaultMessage}" }
            .sorted()
        return ControllerExceptionModel.Builder(HttpStatus.BAD_REQUEST, webRequest, causes).build()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationExceptionHandler(exception: ConstraintViolationException,
                                            webRequest: ServletWebRequest): ControllerExceptionModel {
        val causes = exception.constraintViolations
            .map { "${it.propertyPath}: ${it.message}" }
            .sorted()
        return ControllerExceptionModel.Builder(HttpStatus.BAD_REQUEST, webRequest, causes).build()
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun entityNotFoundExceptionHandler(exception: EntityNotFoundException,
                                       webRequest: ServletWebRequest): ControllerExceptionModel {
        val cause = messageSource.getMessage(exception.message!!, exception.params, LocaleContextHolder.getLocale())
        return ControllerExceptionModel.Builder(HttpStatus.NOT_FOUND, webRequest, listOf(cause)).build()
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SentinelException::class)
    fun sentinelExceptionHandler(exception: SentinelException,
                                 webRequest: ServletWebRequest): ControllerExceptionModel {
        val cause = messageSource.getMessage("sentinel.exception.default", emptyArray(), LocaleContextHolder.getLocale())
        return ControllerExceptionModel.Builder(HttpStatus.INTERNAL_SERVER_ERROR, webRequest, listOf(cause)).build()
    }
}