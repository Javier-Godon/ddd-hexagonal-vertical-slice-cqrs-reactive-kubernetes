package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.SpriteImage.NotValidSpriteImageException;
import org.junit.jupiter.api.Test;

class SpriteImageTest {

  @Test
  void itShouldCreateASpriteImageWhenASpriteImageStringIsProvided() {
    // Given
    String stringSpriteImage = "aG9sYXF1ZRhbA==";
    // When
    SpriteImage underTest = SpriteImage.of(stringSpriteImage);
    // Then
    assertThat(underTest.getValue()).isEqualTo(stringSpriteImage);
  }

  @Test
  void itShouldNotCreateASpriteImageWhenANullIsProvided() {
    assertThatThrownBy(() -> {
      SpriteImage.of(null);
    }).isInstanceOf(NotValidSpriteImageException.class);
  }

  @Test
  void itShouldNotCreateASpriteImageWhenAnEmptyStringIsProvided() {
    // Given
    String stringSpriteImage = "";
    // When
    // Then
    assertThatThrownBy(() -> {
      SpriteImage.of(stringSpriteImage);
    }).isInstanceOf(NotValidSpriteImageException.class);
  }

}