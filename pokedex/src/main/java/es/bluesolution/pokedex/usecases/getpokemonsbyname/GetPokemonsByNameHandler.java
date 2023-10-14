package es.bluesolution.pokedex.usecases.getpokemonsbyname;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import reactor.core.CorePublisher;

public class GetPokemonsByNameHandler implements Handler<Pokemon> {

  private final PokemonRepository pokemonRepository;

  public GetPokemonsByNameHandler(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public CorePublisher<Pokemon> handle(CommandQuery commandQuery) {
    if (!(commandQuery instanceof GetPokemonsByNameQuery query)) {
      throw HandlerConfigurationException.commandQueryNotExpected(
          GetPokemonsByNameQuery.class.getSimpleName(), commandQuery.getClass().getSimpleName());
    }

    return pokemonRepository.findByNameContaining(query.getName());
  }
}
