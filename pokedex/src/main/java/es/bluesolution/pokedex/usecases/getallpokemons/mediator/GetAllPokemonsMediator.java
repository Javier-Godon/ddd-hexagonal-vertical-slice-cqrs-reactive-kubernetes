package es.bluesolution.pokedex.usecases.getallpokemons.mediator;

import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.usecases.getallpokemons.GetAllPokemonsHandler;
import es.bluesolution.pokedex.usecases.getallpokemons.GetAllPokemonsQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllPokemonsMediator {

  private final PokemonRepository pokemonRepository;

  public GetAllPokemonsMediator(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }


  @Bean("getAllPokemonsRegister")
  public CommandQueryMediator getAllPokemonsRegister() {
    final Map<String, Handler> dispatcher = new HashMap<>();

    dispatcher.put(GetAllPokemonsQuery.class.getSimpleName(),
        new GetAllPokemonsHandler(pokemonRepository));
    return new CommandQueryMediator(dispatcher);
  }

}
