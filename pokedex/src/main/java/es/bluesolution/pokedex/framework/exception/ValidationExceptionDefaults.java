package es.bluesolution.pokedex.framework.exception;

import lombok.Getter;

@Getter
public enum ValidationExceptionDefaults {

  NOT_NULL("Null is not a valid value"),
  NOT_BLANK("Blank is not a valid value");

  private final String value;

  ValidationExceptionDefaults(String value) {
    this.value = value;
  }
}
