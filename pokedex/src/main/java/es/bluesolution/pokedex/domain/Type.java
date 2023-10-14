package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.Entity;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import lombok.Getter;

@Getter
public final class Type extends Entity<UUIDIdentifier> {

  private final UUIDIdentifier pokemonId;
  private final FormName formName;

  private Type(UUIDIdentifier id, UUIDIdentifier pokemonId, FormName formName) {
    super(id);
    this.pokemonId = pokemonId;
    this.formName = formName;
  }

  public static Type of(UUIDIdentifier id, UUIDIdentifier pokemonId, FormName formName) {
    return new Type(id, pokemonId, formName);
  }
}
