package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;

import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import org.junit.jupiter.api.Test;

class SpriteTest {

  @Test
  void itShouldCreateASpriteWithNotNullParameters() {
    // Given
    UUIDIdentifier spriteId = UUIDIdentifier.of("995ef134-0bbd-46b1-b6b3-c973145341f2");
    UUIDIdentifier pokemonId = UUIDIdentifier.of("43c3f391-f5a8-4e56-9b21-d66c6965acd6");
    SpriteName spriteName = SpriteName.of("spriteName");
    SpriteImage spriteImage = SpriteImage.of("aG9sYXF1ZRhbA==");
    // When
    Sprite sprite = Sprite.of(
        spriteId,
        pokemonId,
        spriteName,
        spriteImage
    );
    // Then
    assertThat(sprite.getId()).isEqualTo(spriteId);
    assertThat(sprite.getPokemonId()).isEqualTo(pokemonId);
    assertThat(sprite.getSpriteName()).isEqualTo(spriteName);
    assertThat(sprite.getSpriteImage()).isEqualTo(spriteImage);

  }
}