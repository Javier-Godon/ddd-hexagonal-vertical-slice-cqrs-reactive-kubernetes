package es.bluesolution.pokedex.framework.infrastructure;

import es.bluesolution.pokedex.framework.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  protected Mono<ResponseEntity<Object>> handleEntityNotFoundException(RuntimeException exception,
      ServerWebExchange request) {
    ErrorResponse body = new ErrorResponse(exception.getMessage(),
        exception.getClass().getSimpleName());
    return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.NOT_FOUND,
        request);
  }
}
