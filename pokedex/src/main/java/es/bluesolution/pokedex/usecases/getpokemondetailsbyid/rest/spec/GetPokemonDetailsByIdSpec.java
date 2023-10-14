package es.bluesolution.pokedex.usecases.getpokemondetailsbyid.rest.spec;

import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.rest.DetailedPokemonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface GetPokemonDetailsByIdSpec {

  @Operation(summary = "Get a detailed description of a Pokemon")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success retrieving the details"),
      @ApiResponse(responseCode = "404", description = "pokemon not found")
  })
  Mono<DetailedPokemonResponse> getPokemonDetailsById(@PathVariable("id") String id);
}
