package es.bluesolution.pokedex.domain.repository;

import es.bluesolution.pokedex.domain.Sprite;
import java.util.UUID;
import reactor.core.publisher.Flux;

public interface SpriteRepository {

  Flux<Sprite> findByPokemonId(UUID pokemonId);

}
