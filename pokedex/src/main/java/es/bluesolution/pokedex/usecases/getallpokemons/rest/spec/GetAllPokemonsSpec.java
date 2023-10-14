package es.bluesolution.pokedex.usecases.getallpokemons.rest.spec;

import es.bluesolution.pokedex.usecases.getallpokemons.rest.PokemonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;

public interface GetAllPokemonsSpec {

  @Operation(summary = "Get the full list of pokemons")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success retrieving the list")
  })
  Flux<PokemonResponse> getAllPokemons();
}
