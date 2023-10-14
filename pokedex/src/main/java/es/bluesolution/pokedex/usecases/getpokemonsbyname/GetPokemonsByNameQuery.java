package es.bluesolution.pokedex.usecases.getpokemonsbyname;

import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import lombok.Getter;

@Getter
public class GetPokemonsByNameQuery implements CommandQuery {

  private final String name;

  public GetPokemonsByNameQuery(String name) {
    this.name = name;
  }

}
