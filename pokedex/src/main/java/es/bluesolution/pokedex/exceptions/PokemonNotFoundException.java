package es.bluesolution.pokedex.exceptions;

import es.bluesolution.pokedex.framework.exception.EntityNotFoundException;

public class PokemonNotFoundException extends EntityNotFoundException {

  public PokemonNotFoundException(String message) {
    super(message);
  }
}
