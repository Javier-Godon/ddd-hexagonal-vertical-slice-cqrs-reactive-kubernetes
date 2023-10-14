package es.bluesolution.pokedex.usecases.getpokemonsbyname.rest;

import es.bluesolution.pokedex.usecases.getallpokemons.rest.spec.PokemonResponseSpec;
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
public class PokemonResponse implements PokemonResponseSpec {

  private String id;
  private String name;
  private String status;
  private Integer maxWeight;
  private Integer minWeight;
  private Integer maxHeight;
  private Integer minHeight;
  private Integer cp;
  private Integer hp;
  private String cry;


}
