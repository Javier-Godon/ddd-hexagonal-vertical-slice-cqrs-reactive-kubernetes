package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Weight implements ValueObject {

  private final Integer value;

  private Weight(Integer value) {
    if (value == null) {
      throw new NotValidWeightException(ValidationExceptionDefaults.NOT_NULL);
    }
    this.value = value;
  }

  public static Weight of(Integer value) {
    return new Weight(value);
  }

  public static class NotValidWeightException extends DomainException {

    protected NotValidWeightException(String message) {
      super(message);
    }

    protected NotValidWeightException(ValidationExceptionDefaults message) {
      super(message);
    }

  }
}
