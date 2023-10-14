package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.Entity;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import java.util.Objects;
import lombok.Getter;

@Getter
public final class Sprite extends Entity<UUIDIdentifier> {

  private final UUIDIdentifier pokemonId;
  private final SpriteName spriteName;
  private final SpriteImage spriteImage;

  private Sprite(UUIDIdentifier id, UUIDIdentifier pokemonId, SpriteName spriteName,
      SpriteImage spriteImage) {
    super(id);
    this.pokemonId = pokemonId;
    this.spriteName = spriteName;
    this.spriteImage = spriteImage;
  }

  public static Sprite of (UUIDIdentifier id, UUIDIdentifier pokemonId, SpriteName spriteName,
      SpriteImage spriteImage){
    return new Sprite(id, pokemonId, spriteName, spriteImage);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Sprite sprite)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(pokemonId, sprite.pokemonId) && Objects.equals(
        spriteName, sprite.spriteName) && Objects.equals(spriteImage, sprite.spriteImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), pokemonId, spriteName, spriteImage);
  }
}
