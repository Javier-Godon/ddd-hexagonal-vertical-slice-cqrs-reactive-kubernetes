package es.bluesolution.pokedex.usecases.updatepokemonstatus.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePokemonStatusRequest {

  private String pokemonId;
  private String status;

}
