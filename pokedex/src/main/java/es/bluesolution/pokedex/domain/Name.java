package es.bluesolution.pokedex.domain;


import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Name implements ValueObject {

  private final String value;

  private Name(String value) {
    if (value == null) {
      throw new NotValidNameException(ValidationExceptionDefaults.NOT_NULL);
    }
    if (value.isBlank()) {
      throw new NotValidNameException(ValidationExceptionDefaults.NOT_BLANK);
    }
    this.value = value;
  }

  public static Name of(String value) {
    return new Name(value);
  }

  public static class NotValidNameException extends DomainException {

    protected NotValidNameException(String message) {
      super(message);
    }

    protected NotValidNameException(ValidationExceptionDefaults message) {
      super(message);
    }

  }
}
