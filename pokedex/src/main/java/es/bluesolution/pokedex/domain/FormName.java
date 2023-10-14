package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class FormName implements ValueObject {

  private final String value;

  private FormName(String value) {
    if (value == null) {
      throw new NotValidFormNameException(ValidationExceptionDefaults.NOT_NULL);
    }
    if (value.isBlank()) {
      throw new NotValidFormNameException(ValidationExceptionDefaults.NOT_BLANK);
    }
    this.value = value;
  }

  public static FormName of(String value) {
    return new FormName(value);
  }

  public static class NotValidFormNameException extends DomainException {

    protected NotValidFormNameException(String message) {
      super(message);
    }

    protected NotValidFormNameException(ValidationExceptionDefaults message) {
      super(message);
    }

  }

}
