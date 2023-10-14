package es.bluesolution.pokedex.persistence;

import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface SpriteSpringRepository extends R2dbcRepository<SpriteEntity, UUID> {

  Flux<SpriteEntity> findByPokemonId(UUID pokemonId);

}
