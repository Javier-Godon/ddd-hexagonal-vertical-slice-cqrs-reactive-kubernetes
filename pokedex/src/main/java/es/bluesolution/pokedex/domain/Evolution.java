package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.framework.ddd.AggregateRoot;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;

public final class Evolution extends AggregateRoot<UUIDIdentifier> {

  private Evolution(UUIDIdentifier id) {
    super(id);
  }
}
