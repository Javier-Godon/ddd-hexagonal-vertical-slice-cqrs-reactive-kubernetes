package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.Cp.NotValidCpException;
import org.junit.jupiter.api.Test;

class CpTest {

  @Test
  void itShouldCreateACpWhenAnIntegerIsProvided() {
    // Given
    Integer cp = 1000;
    // When
    Cp underTest = Cp.of(cp);
    // Then
    assertThat(underTest.getValue()).isEqualTo(cp);
  }

  @Test
  void itShouldNotCreateACpWhenNoValueIsProvided() {
    // Given
    Integer stringCp = null;
    // When
    // Then
    assertThatThrownBy(() -> {
      Cp.of(null);
    }).isInstanceOf(NotValidCpException.class);
  }

}