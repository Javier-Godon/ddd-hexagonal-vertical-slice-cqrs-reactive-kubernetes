package es.bluesolution.pokedex.usecases.getpokemondetailsbyid;

import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import lombok.Getter;

@Getter
public class GetPokemonDetailsByIdQuery implements CommandQuery {

  private final String id;

  public GetPokemonDetailsByIdQuery(String id) {
    this.id = id;
  }
}
