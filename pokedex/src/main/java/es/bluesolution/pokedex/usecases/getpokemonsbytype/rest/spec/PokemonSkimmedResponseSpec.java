package es.bluesolution.pokedex.usecases.getpokemonsbytype.rest.spec;

import io.swagger.v3.oas.annotations.media.Schema;

public interface PokemonSkimmedResponseSpec {

  @Schema(description = "The ID of the Pokemon. It must be a valid UUID",
      example = "ee7b1e48-1fbb-4968-b62b-9380028615fe")
  String getId();

}
