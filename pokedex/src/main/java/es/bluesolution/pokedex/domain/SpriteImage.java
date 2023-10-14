package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class SpriteImage implements ValueObject {

  private final String value;

  private SpriteImage(String value) {
    if (value == null) {
      throw new NotValidSpriteImageException(ValidationExceptionDefaults.NOT_NULL);
    }
    if (value.isBlank()) {
      throw new NotValidSpriteImageException(ValidationExceptionDefaults.NOT_BLANK);
    }
    this.value = value;
  }

  public static SpriteImage of(String value) {
    return new SpriteImage(value);
  }

  public static class NotValidSpriteImageException extends DomainException {

    protected NotValidSpriteImageException(String message) {
      super(message);
    }

    protected NotValidSpriteImageException(ValidationExceptionDefaults message) {
      super(message);
    }
  }

}
