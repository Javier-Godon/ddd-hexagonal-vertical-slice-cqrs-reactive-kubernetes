package es.bluesolution.pokedex.usecases.getpokemonsbyname.mediator;

import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.usecases.getpokemonsbyname.GetPokemonsByNameHandler;
import es.bluesolution.pokedex.usecases.getpokemonsbyname.GetPokemonsByNameQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetPokemonsByNameMediator {

  private final PokemonRepository pokemonRepository;

  public GetPokemonsByNameMediator(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }


  @Bean("getPokemonsByNameRegister")
  public CommandQueryMediator getPokemonsByNameRegister() {
    final Map<String, Handler> dispatcher = new HashMap<>();

    dispatcher.put(GetPokemonsByNameQuery.class.getSimpleName(),
        new GetPokemonsByNameHandler(pokemonRepository));
    return new CommandQueryMediator(dispatcher);
  }

}
