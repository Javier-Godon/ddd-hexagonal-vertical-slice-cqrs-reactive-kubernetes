package es.bluesolution.pokedex.domain.repository;

import es.bluesolution.pokedex.domain.Pokemon;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokemonRepository {

  Flux<Pokemon> findAll();

  Mono<Pokemon> findById(UUID id);

  Flux<Pokemon> findByNameContaining(String name);

  Flux<Pokemon> findByType(String type);

  Mono<Pokemon> save(Pokemon pokemon);

}
