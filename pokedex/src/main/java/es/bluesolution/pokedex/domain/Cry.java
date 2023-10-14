package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Cry implements ValueObject {

  private final String value;

  private Cry(String value) {
    if (value == null) {
      throw new NotValidCryException(ValidationExceptionDefaults.NOT_NULL);
    }
    if (value.isBlank()) {
      throw new NotValidCryException(ValidationExceptionDefaults.NOT_BLANK);
    }
    this.value = value;
  }

  public static Cry of(String value) {
    return new Cry(value);
  }

  public static class NotValidCryException extends DomainException {

    protected NotValidCryException(String message) {
      super(message);
    }

    protected NotValidCryException(ValidationExceptionDefaults message) {
      super(message);
    }
  }
}
