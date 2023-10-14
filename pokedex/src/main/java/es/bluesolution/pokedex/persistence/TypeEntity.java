package es.bluesolution.pokedex.persistence;

import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "pokemon_type", schema = "pokedex")
public class TypeEntity implements Persistable<UUID> {

  @Id
  private UUID id;
  @Getter
  private UUID pokemonId;
  @Getter
  private String formName;


  @Transient
  private boolean isNew;

  public TypeEntity() {
  }

  public TypeEntity(UUID id, UUID pokemonId, String formName) {
    this.id = id;
    this.pokemonId = pokemonId;
    this.formName = formName;
  }

  @Override
  @Transient
  public boolean isNew() {
    return this.isNew;
  }

  @Override
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setPokemonId(UUID pokemonId) {
    this.pokemonId = pokemonId;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TypeEntity that)) {
      return false;
    }
    return Objects.equals(id, that.id) && Objects.equals(pokemonId,
        that.pokemonId) && Objects.equals(formName, that.formName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pokemonId, formName);
  }
}
