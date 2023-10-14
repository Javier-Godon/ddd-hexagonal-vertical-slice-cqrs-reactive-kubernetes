package es.bluesolution.pokedex.framework.exception;

public class EntityNotFoundException extends DomainException{

  protected EntityNotFoundException(String message) {
    super(message);
  }
}
