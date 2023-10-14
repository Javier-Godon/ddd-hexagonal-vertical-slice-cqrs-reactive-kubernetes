package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.ValueObject;
import es.bluesolution.pokedex.framework.exception.DomainException;
import es.bluesolution.pokedex.framework.exception.ValidationExceptionDefaults;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Status implements ValueObject {

  private final PokemonStatusEnum value;

  private Status(PokemonStatusEnum value) {
    if (value == null) {
      throw new NotValidPokemonStatusException(ValidationExceptionDefaults.NOT_NULL);
    }
    this.value = value;
  }

  public static Status of(PokemonStatusEnum value) {
    return new Status(value);
  }

  public static Status of(String value) {
    try {
      return new Status(PokemonStatusEnum.valueOf(value));
    } catch (IllegalArgumentException e) {
      String allowedValues = Arrays.stream(PokemonStatusEnum.values())
          .map(PokemonStatusEnum::toString).collect(
              Collectors.joining(", "));
      throw new NotValidPokemonStatusException(
          String.format("%s is not a valid value, it should be one of: %s", value, allowedValues));
    }

  }

  public static class NotValidPokemonStatusException extends DomainException {

    protected NotValidPokemonStatusException(String message) {
      super(message);
    }

    protected NotValidPokemonStatusException(ValidationExceptionDefaults message) {
      super(message);
    }

  }

}
