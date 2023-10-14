package es.bluesolution.pokedex.framework.ddd;

import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Entity<T extends Identifier> {

  private final T id;

  protected Entity(T id) {
    this.id = Objects.requireNonNull(id);
  }

}
