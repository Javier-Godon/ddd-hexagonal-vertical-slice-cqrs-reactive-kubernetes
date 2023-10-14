package es.bluesolution.pokedex.usecases.getpokemondetailsbyid;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.Type;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetPokemonDetailsByIdResult {

  private Pokemon pokemon;
  private List<Type> types;

}
