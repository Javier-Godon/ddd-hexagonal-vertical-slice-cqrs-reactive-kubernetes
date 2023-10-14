package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Cp implements ValueObject {

  private final Integer value;

  private Cp(Integer value) {
    if (value == null) {
      throw new NotValidCpException(ValidationExceptionDefaults.NOT_NULL);
    }
    this.value = value;
  }

  public static Cp of(Integer value) {
    return new Cp(value);
  }

  public static class NotValidCpException extends DomainException {

    protected NotValidCpException(String message) {
      super(message);
    }

    protected NotValidCpException(ValidationExceptionDefaults message) {
      super(message);
    }

  }
}
