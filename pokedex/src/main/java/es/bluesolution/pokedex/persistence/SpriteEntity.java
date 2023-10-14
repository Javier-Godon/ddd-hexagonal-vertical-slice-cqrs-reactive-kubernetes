package es.bluesolution.pokedex.persistence;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "pokemon_sprite", schema = "pokedex")
public class SpriteEntity {

  @Id
  private UUID id;
  private UUID pokemonId;
  private String spriteName;
  private byte[] spriteImage;

}
