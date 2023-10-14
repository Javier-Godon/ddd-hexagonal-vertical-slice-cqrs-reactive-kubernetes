package es.bluesolution.pokedex.usecases.getallpokemons;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;

public class GetAllPokemonsHandler implements Handler<Pokemon> {

  private final PokemonRepository pokemonRepository;

  public GetAllPokemonsHandler(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public CorePublisher<Pokemon> handle(CommandQuery commandQuery) {
    if (!(commandQuery instanceof GetAllPokemonsQuery)) {
      throw HandlerConfigurationException.commandQueryNotExpected(
          GetAllPokemonsQuery.class.getSimpleName(), commandQuery.getClass().getSimpleName());
    }
    return pokemonRepository.findAll();
  }
}
