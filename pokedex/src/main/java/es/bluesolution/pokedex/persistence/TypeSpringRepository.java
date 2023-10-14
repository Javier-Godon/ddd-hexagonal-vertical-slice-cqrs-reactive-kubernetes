package es.bluesolution.pokedex.persistence;

import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface TypeSpringRepository extends R2dbcRepository<TypeEntity, UUID> {

  Flux<TypeEntity> findByPokemonId(UUID pokemonId);

  Flux<TypeEntity> findByFormNameContaining(String type);

}
