package es.bluesolution.pokedex.usecases.updatepokemonstatus.infrastructure.rest.spec;

import es.bluesolution.pokedex.usecases.updatepokemonstatus.infrastructure.rest.UpdatePokemonStatusRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface UpdatePokemonStatusSpec {

  @Operation(summary = "Updates the status of a Pokemon")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "successfully updated")
  })
  public Mono<Object> updatePokemonStatus(@RequestBody UpdatePokemonStatusRequest request);

}
