package es.bluesolution.pokedex.usecases.getpokemondetailsbyid.rest;

import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.GetPokemonDetailsByIdQuery;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.GetPokemonDetailsByIdResult;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.rest.spec.GetPokemonDetailsByIdSpec;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/pokedex")
public class GetPokemonDetailsByIdResource implements GetPokemonDetailsByIdSpec {

  private final CommandQueryMediator mediator;

  public GetPokemonDetailsByIdResource(
      @Qualifier("getPokemonDetailsByIdRegister") CommandQueryMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  @GetMapping(value = "/pokemon/details/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<DetailedPokemonResponse> getPokemonDetailsById(@PathVariable("id") String id) {
    GetPokemonDetailsByIdQuery query = new GetPokemonDetailsByIdQuery(id);
    CorePublisher<Object> result = mediator.execute(query);
    Mono<GetPokemonDetailsByIdResult> pokemonDetailsByIdResultMono = Mono.from(result)
        .map(GetPokemonDetailsByIdResult.class::cast);

    return pokemonDetailsByIdResultMono.map(fromDomainToResponse);

  }

  private final Function<GetPokemonDetailsByIdResult, DetailedPokemonResponse> fromDomainToResponse = getPokemonDetailsByIdResult -> {

    List<TypeResponse> typeResponses = getPokemonDetailsByIdResult.getTypes().stream()
        .map(type -> TypeResponse.builder().formName(type.getFormName().getValue()).build())
        .toList();

    return DetailedPokemonResponse.builder()
        .id(getPokemonDetailsByIdResult.getPokemon().getId().getUuid().toString())
        .name(getPokemonDetailsByIdResult.getPokemon().getName().getValue())
        .status(getPokemonDetailsByIdResult.getPokemon().getStatus().getValue().name())
        .maxWeight(getPokemonDetailsByIdResult.getPokemon().getMaxWeight().getValue())
        .minWeight(getPokemonDetailsByIdResult.getPokemon().getMinWeight().getValue())
        .maxHeight(getPokemonDetailsByIdResult.getPokemon().getMaxHeight().getValue())
        .minHeight(getPokemonDetailsByIdResult.getPokemon().getMinHeight().getValue())
        .cp(getPokemonDetailsByIdResult.getPokemon().getCp().getValue())
        .hp(getPokemonDetailsByIdResult.getPokemon().getHp().getValue())
        .cry(getPokemonDetailsByIdResult.getPokemon().getCry().getValue())
        .types(typeResponses)
        .build();
  };


}
