package es.bluesolution.pokedex.framework.ddd;

/**
 * Cluster of entities and value objects that can be treated as a whole. It has a consistent state.
 * The aggregate root is the only point of access to the aggregate, so another aggregate or entity
 * cannot access any object within the aggregate.
 */
public abstract class AggregateRoot<T extends Identifier> extends Entity<T> {

  protected AggregateRoot(T id) {
    super(id);
  }
}
