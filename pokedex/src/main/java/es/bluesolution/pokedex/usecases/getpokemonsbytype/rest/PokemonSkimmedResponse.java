package es.bluesolution.pokedex.usecases.getpokemonsbytype.rest;

import es.bluesolution.pokedex.usecases.getpokemonsbytype.rest.spec.PokemonSkimmedResponseSpec;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonSkimmedResponse implements PokemonSkimmedResponseSpec {

  private String id;


}
