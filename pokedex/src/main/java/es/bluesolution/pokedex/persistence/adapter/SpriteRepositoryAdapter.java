package es.bluesolution.pokedex.persistence.adapter;

import es.bluesolution.pokedex.domain.Sprite;
import es.bluesolution.pokedex.domain.SpriteImage;
import es.bluesolution.pokedex.domain.SpriteName;
import es.bluesolution.pokedex.domain.repository.SpriteRepository;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import es.bluesolution.pokedex.persistence.SpriteEntity;
import es.bluesolution.pokedex.persistence.SpriteSpringRepository;
import java.util.Base64;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SpriteRepositoryAdapter implements SpriteRepository {

  SpriteSpringRepository springRepository;

  public Flux<Sprite> findByPokemonId(UUID pokemonId) {
    return springRepository.findByPokemonId(pokemonId).flatMap(fromEntityToDomain);
  }

  private final Function<SpriteEntity, Mono<Sprite>> fromEntityToDomain = spriteEntity ->
      Mono.just(Sprite.of(
          UUIDIdentifier.of(spriteEntity.getId()),
          UUIDIdentifier.of(spriteEntity.getPokemonId()),
          SpriteName.of(spriteEntity.getSpriteName()),
          SpriteImage.of(Base64.getEncoder().encodeToString(spriteEntity.getSpriteImage()))
      ));
}
