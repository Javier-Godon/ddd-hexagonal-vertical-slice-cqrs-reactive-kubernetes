package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.Hp.NotValidHpException;
import org.junit.jupiter.api.Test;

class HpTest {

  @Test
  void itShouldCreateAHpWhenAnIntegerIsProvided() {
    // Given
    Integer hp = 1000;
    // When
    Hp underTest = Hp.of(hp);
    // Then
    assertThat(underTest.getValue()).isEqualTo(hp);
  }

  @Test
  void itShouldNotCreateAHpWhenNoValueIsProvided() {
    // Given
    Integer stringHp = null;
    // When
    // Then
    assertThatThrownBy(() -> {
      Hp.of(null);
    }).isInstanceOf(NotValidHpException.class);
  }

}