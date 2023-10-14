package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Height implements ValueObject {

  private final Integer value;

  private Height(Integer value) {
    if (value == null) {
      throw new NotValidHeightException(ValidationExceptionDefaults.NOT_NULL);
    }
    this.value = value;
  }

  public static Height of(Integer value) {
    return new Height(value);
  }

  public static class NotValidHeightException extends DomainException {

    protected NotValidHeightException(String message) {
      super(message);
    }

    protected NotValidHeightException(ValidationExceptionDefaults message) {
      super(message);
    }

  }
}
