package es.bluesolution.pokedex.usecases.getpokemonsbytype.mediator;

import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.usecases.getpokemonsbytype.GetPokemonsByTypeHandler;
import es.bluesolution.pokedex.usecases.getpokemonsbytype.GetPokemonsByTypeQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetPokemonsByTypeMediator {

  private final TypeRepository typeRepository;

  public GetPokemonsByTypeMediator(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }


  @Bean("getPokemonsByTypeRegister")
  public CommandQueryMediator getPokemonsByTypeRegister() {
    final Map<String, Handler> dispatcher = new HashMap<>();

    dispatcher.put(GetPokemonsByTypeQuery.class.getSimpleName(),
        new GetPokemonsByTypeHandler(typeRepository));
    return new CommandQueryMediator(dispatcher);
  }

}
