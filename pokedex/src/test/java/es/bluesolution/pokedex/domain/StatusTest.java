package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import es.bluesolution.pokedex.domain.Status.NotValidPokemonStatusException;
import org.junit.jupiter.api.Test;

class StatusTest {

  @Test
  void itShouldCreateAStatusWhenARightStatusStringIsProvided() {
    // Given
    String stringStatus = "FAVORITE";
    // When
    Status underTest = Status.of(stringStatus);
    // Then
    assertThat(underTest.getValue().name()).isEqualTo(stringStatus);
  }

  @Test
  void itShouldNotCreateAStatusWhenAWrongStatusStringIsProvided() {
    // Given
    String stringStatus = "WRONG-STATUS";
    // When
    // Then
    assertThatThrownBy(() -> {
      Status.of(stringStatus);
    }).isInstanceOf(NotValidPokemonStatusException.class);
  }

  @Test
  void itShouldNotCreateAStatusWhenAnEmptyStringIsProvided() {
    // Given
    String stringStatus = "";
    // When
    // Then
    assertThatThrownBy(() -> {
      Status.of(stringStatus);
    }).isInstanceOf(NotValidPokemonStatusException.class);
  }

  @Test
  void itShouldCreateAStatusWhenARightStatusFromEnumIsProvided() {
    // Given
    PokemonStatusEnum favorite = PokemonStatusEnum.FAVORITE;
    // When
    Status underTest = Status.of(favorite);
    // Then
    assertThat(underTest.getValue()).isEqualTo(favorite);
  }

}