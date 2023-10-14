package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.Height.NotValidHeightException;
import org.junit.jupiter.api.Test;

class HeightTest {

  @Test
  void itShouldCreateAHeightWhenAnIntegerIsProvided() {
    // Given
    Integer height = 1000;
    // When
    Height underTest = Height.of(height);
    // Then
    assertThat(underTest.getValue()).isEqualTo(height);
  }

  @Test
  void itShouldNotCreateAHeightWhenNoValueIsProvided() {
    // Given
    Integer stringHeight = null;
    // When
    // Then
    assertThatThrownBy(() -> {
      Height.of(null);
    }).isInstanceOf(NotValidHeightException.class);
  }

}