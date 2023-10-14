package es.bluesolution.pokedex.usecases.getallpokemons.rest;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.usecases.getallpokemons.GetAllPokemonsQuery;
import es.bluesolution.pokedex.usecases.getallpokemons.rest.spec.GetAllPokemonsSpec;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/pokedex")
public class GetAllPokemonsResource implements GetAllPokemonsSpec {

  private final CommandQueryMediator mediator;

  public GetAllPokemonsResource(
      @Qualifier("getAllPokemonsRegister") CommandQueryMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  @GetMapping(value = "/pokemon", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<PokemonResponse> getAllPokemons() {
    GetAllPokemonsQuery query = new GetAllPokemonsQuery();
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
