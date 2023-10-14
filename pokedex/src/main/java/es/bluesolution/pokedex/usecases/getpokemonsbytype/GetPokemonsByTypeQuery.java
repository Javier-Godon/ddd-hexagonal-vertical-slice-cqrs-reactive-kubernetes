package es.bluesolution.pokedex.usecases.getpokemonsbytype;

import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import lombok.Getter;

@Getter
public class GetPokemonsByTypeQuery implements CommandQuery {

  private final String type;

  public GetPokemonsByTypeQuery(String type) {
    this.type = type;
  }
}
