package es.bluesolution.pokedex.usecases.getallpokemons.rest.spec;

import es.bluesolution.pokedex.domain.PokemonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public interface PokemonResponseSpec {

  @Schema(description = "The ID of the Pokemon. It must be a valid UUID",
      example = "ee7b1e48-1fbb-4968-b62b-9380028615fe")
  String getId();

  @Schema(description = "The NAME of the Pokemon. ",
      example = "Pikachu")
  String getName();

  @Schema(description = "The STATUS of the Pokemon.",
      type = "string",
      implementation = PokemonStatusEnum.class,
      example = "REGULAR")
  String getStatus();

  @Schema(description = "The MAXIMUM WEIGHT of the Pokemon in hectograms",
      example = "1000")
  Integer getMaxWeight();

  @Schema(description = "The MINIMUM WEIGHT of the Pokemon in hectograms",
      type = "integer",
      example = "1000")
  Integer getMinWeight();

  @Schema(description = "The MAXIMUM HEIGHT of the Pokemon in hectograms",
      example = "1000")
  Integer getMaxHeight();

  @Schema(description = "The MAXIMUM HEIGHT of the Pokemon in hectograms",
      example = "1000")
  Integer getMinHeight();

  @Schema(description = "The CP of the Pokemon.",
      example = "1000")
  Integer getCp();

  @Schema(description = "The HP of the Pokemon.",
      example = "1000")
  Integer getHp();

  @Schema(description = "The CRY of the Pokemon. Base64 format",
      example = "aG9sYXF1ZRhbA==")
  String getCry();

}
