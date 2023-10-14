package es.bluesolution.pokedex.usecases.getpokemonsbytype.rest.spec;

import es.bluesolution.pokedex.usecases.getpokemonsbytype.rest.PokemonSkimmedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

public interface GetPokemonsByTypeSpec {

  @Operation(summary = "Get a list of pokemons that belong to the given type")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success retrieving the list")
  })
  Flux<PokemonSkimmedResponse> getPokemonsByType(@PathVariable("type") String type);

}
