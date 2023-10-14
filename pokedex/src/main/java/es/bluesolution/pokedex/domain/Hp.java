package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Hp implements ValueObject {

  private final Integer value;

  private Hp(Integer value) {
    if (value == null) {
      throw new NotValidHpException(ValidationExceptionDefaults.NOT_NULL);
    }
    this.value = value;
  }

  public static Hp of(Integer value) {
    return new Hp(value);
  }

  public static class NotValidHpException extends DomainException {

    protected NotValidHpException(String message) {
      super(message);
    }

    protected NotValidHpException(ValidationExceptionDefaults message) {
      super(message);
    }

  }
}
