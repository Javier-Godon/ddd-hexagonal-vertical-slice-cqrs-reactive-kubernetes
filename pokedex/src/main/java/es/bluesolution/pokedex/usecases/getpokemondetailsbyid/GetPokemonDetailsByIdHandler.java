package es.bluesolution.pokedex.usecases.getpokemondetailsbyid;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.Type;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.exceptions.PokemonNotFoundException;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import java.util.List;
import java.util.UUID;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

public class GetPokemonDetailsByIdHandler implements Handler<GetPokemonDetailsByIdResult> {

  private final PokemonRepository pokemonRepository;

  private final TypeRepository typeRepository;

  public GetPokemonDetailsByIdHandler(PokemonRepository pokemonRepository,
      TypeRepository typeRepository) {
    this.pokemonRepository = pokemonRepository;
    this.typeRepository = typeRepository;
  }

  @Override
  public CorePublisher<GetPokemonDetailsByIdResult> handle(CommandQuery commandQuery) {
    if (!(commandQuery instanceof GetPokemonDetailsByIdQuery query)) {
      throw HandlerConfigurationException.commandQueryNotExpected(
          GetPokemonDetailsByIdQuery.class.getSimpleName(),
          commandQuery.getClass().getSimpleName());
    }

    Mono<Pokemon> pokemon = pokemonRepository.findById(UUID.fromString(query.getId()))
        .switchIfEmpty(Mono.defer(() -> Mono.error(new PokemonNotFoundException(
            String.format("Pokemon with id %s not found", query.getId())))));
    Mono<List<Type>> types = typeRepository.findByPokemonId(UUID.fromString(query.getId()))
        .collectList();
    return Mono.zip(
        pokemon,
        types,
        (pokemonResult, typesList) -> GetPokemonDetailsByIdResult.builder().pokemon(pokemonResult)
            .types(typesList).build());
  }
}
