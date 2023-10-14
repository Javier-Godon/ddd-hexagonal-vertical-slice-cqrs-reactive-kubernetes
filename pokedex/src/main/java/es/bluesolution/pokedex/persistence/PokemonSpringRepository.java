package es.bluesolution.pokedex.persistence;

import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface PokemonSpringRepository extends R2dbcRepository<PokemonEntity, UUID> {

  Flux<PokemonEntity> findByNameContaining(String name);

}
