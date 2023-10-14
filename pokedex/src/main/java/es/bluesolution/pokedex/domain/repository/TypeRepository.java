package es.bluesolution.pokedex.domain.repository;

import es.bluesolution.pokedex.domain.Type;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeRepository {

  Mono<Type> findById(UUID id);

  Flux<Type> findByPokemonId(UUID id);

  Flux<Type> findByFormNameContaining(String type);
}
