package es.bluesolution.pokedex.usecases.updatepokemonstatus.mediator;

import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.usecases.updatepokemonstatus.application.UpdatePokemonStatusCommand;
import es.bluesolution.pokedex.usecases.updatepokemonstatus.application.UpdatePokemonStatusHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdatePokemonStatusMediator {

  private final PokemonRepository pokemonRepository;

  public UpdatePokemonStatusMediator(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }


  @Bean("updatePokemonStatusRegister")
  public CommandQueryMediator updatePokemonStatusRegister() {
    final Map<String, Handler> dispatcher = new HashMap<>();

    dispatcher.put(UpdatePokemonStatusCommand.class.getSimpleName(),
        new UpdatePokemonStatusHandler(pokemonRepository));
    return new CommandQueryMediator(dispatcher);
  }

}
