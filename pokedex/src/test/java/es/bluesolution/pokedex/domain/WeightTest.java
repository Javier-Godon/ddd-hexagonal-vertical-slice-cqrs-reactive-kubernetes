package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.Weight.NotValidWeightException;
import org.junit.jupiter.api.Test;

class WeightTest {

  @Test
  void itShouldCreateAWeightWhenAnIntegerIsProvided() {
    // Given
    Integer weight = 1000;
    // When
    Weight underTest = Weight.of(weight);
    // Then
    assertThat(underTest.getValue()).isEqualTo(weight);
  }

  @Test
  void itShouldNotCreateAWeightWhenNoValueIsProvided() {
    // Given
    Integer stringWeight = null;
    // When
    // Then
    assertThatThrownBy(() -> {
      Weight.of(null);
    }).isInstanceOf(NotValidWeightException.class);
  }

}