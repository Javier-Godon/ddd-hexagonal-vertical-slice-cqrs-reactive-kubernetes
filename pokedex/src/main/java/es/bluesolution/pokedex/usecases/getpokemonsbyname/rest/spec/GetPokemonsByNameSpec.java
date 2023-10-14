package es.bluesolution.pokedex.usecases.getpokemonsbyname.rest.spec;

import es.bluesolution.pokedex.usecases.getpokemonsbyname.rest.PokemonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

public interface GetPokemonsByNameSpec {

  @Operation(summary = "Get a Pokemon searching by name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success retrieving the pokemon"),
      @ApiResponse(responseCode = "404", description = "pokemon not found")
  })
  Flux<PokemonResponse> getPokemonsByName(@PathVariable("name") String name);

}
