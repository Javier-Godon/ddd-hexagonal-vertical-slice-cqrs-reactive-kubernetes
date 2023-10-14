package es.bluesolution.pokedex.usecases.getpokemonsbyname.rest;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.usecases.getpokemonsbyname.GetPokemonsByNameQuery;
import es.bluesolution.pokedex.usecases.getpokemonsbyname.rest.spec.GetPokemonsByNameSpec;
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
public class GetPokemonsByNameResource implements GetPokemonsByNameSpec {

  private final CommandQueryMediator mediator;

  public GetPokemonsByNameResource(
      @Qualifier("getPokemonsByNameRegister") CommandQueryMediator mediator) {
    this.mediator = mediator;
  }

  @GetMapping(value = "/pokemon/by-name/{name}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<PokemonResponse> getPokemonsByName(@PathVariable("name") String name) {
    GetPokemonsByNameQuery query = new GetPokemonsByNameQuery(name);
    CorePublisher<Object> result = mediator.execute(query);
    Flux<Pokemon> pokemonFlux = Flux.from(result).map(Pokemon.class::cast);
    return pokemonFlux.flatMap(fromDomainToResponse);

  }

  private final Function<Pokemon, Flux<PokemonResponse>> fromDomainToResponse = pokemon ->
      Flux.just(PokemonResponse.builder()
          .id(pokemon.getId().getUuid().toString())
          .name(pokemon.getName().getValue())
          .status(pokemon.getStatus().getValue().name())
          .maxWeight(pokemon.getMaxWeight().getValue())
          .minWeight(pokemon.getMinWeight().getValue())
          .maxHeight(pokemon.getMaxHeight().getValue())
          .minHeight(pokemon.getMinHeight().getValue())
          .cp(pokemon.getCp().getValue())
          .hp(pokemon.getHp().getValue())
          .cry(pokemon.getCry().getValue())
          .build());

}
