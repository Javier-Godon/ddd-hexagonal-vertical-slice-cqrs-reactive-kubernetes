package es.bluesolution.pokedex.usecases.updatepokemonstatus.application;

import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdatePokemonStatusCommand implements CommandQuery {

  private final String pokemonId;
  private final String status;

  public UpdatePokemonStatusCommand(String pokemonId, String type) {
    this.pokemonId = pokemonId;
    this.status = type;
  }
}
