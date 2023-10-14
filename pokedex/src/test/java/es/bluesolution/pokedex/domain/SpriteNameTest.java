package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.SpriteName.NotValidSpriteNameException;
import org.junit.jupiter.api.Test;

class SpriteNameTest {

  @Test
  void itShouldCreateASpriteNameWhenASpriteNameStringIsProvided() {
    // Given
    String stringSpriteName = "spriteName";
    // When
    SpriteName underTest = SpriteName.of(stringSpriteName);
    // Then
    assertThat(underTest.getValue()).isEqualTo(stringSpriteName);
  }

  @Test
  void itShouldNotCreateASpriteNameWhenANullIsProvided() {
    assertThatThrownBy(() -> {
      SpriteName.of(null);
    }).isInstanceOf(NotValidSpriteNameException.class);
  }

  @Test
  void itShouldNotCreateASpriteNameWhenAnEmptyStringIsProvided() {
    // Given
    String stringSpriteName = "";
    // When
    // Then
    assertThatThrownBy(() -> {
      SpriteName.of(stringSpriteName);
    }).isInstanceOf(NotValidSpriteNameException.class);
  }


}