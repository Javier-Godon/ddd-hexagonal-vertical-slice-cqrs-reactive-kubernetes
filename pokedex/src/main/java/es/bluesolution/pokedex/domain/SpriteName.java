package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class SpriteName implements ValueObject {

  private final String value;

  private SpriteName(String value) {
    if (value == null) {
      throw new NotValidSpriteNameException(ValidationExceptionDefaults.NOT_NULL);
    }
    if (value.isBlank()) {
      throw new NotValidSpriteNameException(ValidationExceptionDefaults.NOT_BLANK);
    }
    this.value = value;
  }

  public static SpriteName of(String value) {
    return new SpriteName(value);
  }

  public static class NotValidSpriteNameException extends DomainException {

    protected NotValidSpriteNameException(String message) {
      super(message);
    }

    protected NotValidSpriteNameException(ValidationExceptionDefaults message) {
      super(message);
    }

  }

}
