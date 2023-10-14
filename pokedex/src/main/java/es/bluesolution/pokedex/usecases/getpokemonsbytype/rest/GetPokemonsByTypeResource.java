package es.bluesolution.pokedex.usecases.getpokemonsbytype.rest;

import es.bluesolution.pokedex.domain.Type;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.usecases.getpokemonsbytype.GetPokemonsByTypeQuery;
import es.bluesolution.pokedex.usecases.getpokemonsbytype.rest.spec.GetPokemonsByTypeSpec;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/pokedex")
public class GetPokemonsByTypeResource implements GetPokemonsByTypeSpec {

  private final CommandQueryMediator mediator;

  public GetPokemonsByTypeResource(
      @Qualifier("getPokemonsByTypeRegister") CommandQueryMediator mediator) {
    this.mediator = mediator;
  }

  @GetMapping(value = "/pokemon/by-type/{type}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<PokemonSkimmedResponse> getPokemonsByType(@PathVariable("type") String type) {
    GetPokemonsByTypeQuery query = new GetPokemonsByTypeQuery(type);
    CorePublisher<Object> result = mediator.execute(query);
    return Flux.from(result).map(Type.class::cast).
        flatMap(fromTypeToPokemonSkimmedResponse);
  }

  private final Function<Type, Flux<PokemonSkimmedResponse>> fromTypeToPokemonSkimmedResponse = pokemon ->
      Flux.just(PokemonSkimmedResponse.builder()
          .id(pokemon.getId().getUuid().toString())
          .build());

}
