package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.Cry.NotValidCryException;
import org.junit.jupiter.api.Test;

class CryTest {

  @Test
  void itShouldCreateACryWhenACryStringIsProvided() {
    // Given
    String stringCry = "aG9sYXF1ZRhbA==";
    // When
    Cry underTest = Cry.of(stringCry);
    // Then
    assertThat(underTest.getValue()).isEqualTo(stringCry);
  }

  @Test
  void itShouldNotCreateACryWhenANullIsProvided() {
    assertThatThrownBy(() -> {
      Cry.of(null);
    }).isInstanceOf(NotValidCryException.class);
  }

  @Test
  void itShouldNotCreateACryWhenAnEmptyStringIsProvided() {
    // Given
    String stringCry = "";
    // When
    // Then
    assertThatThrownBy(() -> {
      Cry.of(stringCry);
    }).isInstanceOf(NotValidCryException.class);
  }

}