package es.bluesolution.pokedex.usecases.getpokemondetailsbyid.mediator;

import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.GetPokemonDetailsByIdHandler;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.GetPokemonDetailsByIdQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetPokemonDetailsByIdMediator {

  private final PokemonRepository pokemonRepository;
  private final TypeRepository typeRepository;

  public GetPokemonDetailsByIdMediator(PokemonRepository pokemonRepository,
      TypeRepository typeRepository) {
    this.pokemonRepository = pokemonRepository;
    this.typeRepository = typeRepository;
  }


  @Bean("getPokemonDetailsByIdRegister")
  public CommandQueryMediator getPokemonDetailsByIdRegister() {
    final Map<String, Handler> dispatcher = new HashMap<>();

    dispatcher.put(GetPokemonDetailsByIdQuery.class.getSimpleName(),
        new GetPokemonDetailsByIdHandler(pokemonRepository, typeRepository));
    return new CommandQueryMediator(dispatcher);
  }

}
