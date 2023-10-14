package es.bluesolution.pokedex.usecases.updatepokemonstatus.infrastructure.rest.spec;

import es.bluesolution.pokedex.domain.PokemonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public interface UpdatePokemonStatusRequestSpec {

  @Schema(description = "The ID of the Pokemon. It must be a valid UUID",
      example = "ee7b1e48-1fbb-4968-b62b-9380028615fe")
  String getPokemonId();

  @Schema(description = "The STATUS of the Pokemon.",
      type = "string",
      implementation = PokemonStatusEnum.class,
      example = "REGULAR")
  String getStatus();


}
