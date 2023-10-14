package es.bluesolution.pokedex.framework.cqrs;

public interface Mediator<T> {
  Object execute(T commandQuery);
}
