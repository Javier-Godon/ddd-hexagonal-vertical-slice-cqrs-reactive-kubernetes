package es.bluesolution.pokedex.persistence;

import es.bluesolution.pokedex.domain.Pokemon;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "pokemon", schema = "pokedex")
public class PokemonEntity implements Persistable<UUID> {

  @Id
  private UUID id;
  @Getter
  private String name;
  @Getter
  private String status;
  @Getter
  private Integer maxWeight;
  @Getter
  private Integer minWeight;
  @Getter
  private Integer maxHeight;
  @Getter
  private Integer minHeight;
  @Getter
  private Integer cp;
  @Getter
  private Integer hp;
  @Getter
  private byte[] cry;

  @Transient
  private boolean isNew;

  public PokemonEntity() {
  }

  public PokemonEntity(UUID id, String name, String status, Integer maxWeight, Integer minWeight,
      Integer maxHeight, Integer minHeight, Integer cp, Integer hp, byte[] cry) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.maxWeight = maxWeight;
    this.minWeight = minWeight;
    this.maxHeight = maxHeight;
    this.minHeight = minHeight;
    this.cp = cp;
    this.hp = hp;
    this.cry = cry;
  }

  public static PokemonEntity of(Pokemon pokemon) {
    return new PokemonEntity(
        pokemon.getId().getUuid(),
        pokemon.getName().getValue(),
        pokemon.getStatus().getValue().name(),
        pokemon.getMaxWeight().getValue(),
        pokemon.getMinWeight().getValue(),
        pokemon.getMaxHeight().getValue(),
        pokemon.getMinHeight().getValue(),
        pokemon.getCp().getValue(),
        pokemon.getHp().getValue(),
        pokemon.getCry() == null ? null : Base64.getDecoder().decode(pokemon.getCry().getValue()));
  }


  @Override
  @Transient
  public boolean isNew() {
    return this.isNew;
  }

  public void setAsCreate() {
    this.isNew = true;
  }

  public void setCry(String cry) {
    this.cry = cry == null ? null : Base64.getDecoder().decode(cry);
  }

  @Override
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setMaxWeight(Integer maxWeight) {
    this.maxWeight = maxWeight;
  }

  public void setMinWeight(Integer minWeight) {
    this.minWeight = minWeight;
  }

  public void setMaxHeight(Integer maxHeight) {
    this.maxHeight = maxHeight;
  }

  public void setMinHeight(Integer minHeight) {
    this.minHeight = minHeight;
  }

  public void setCp(Integer cp) {
    this.cp = cp;
  }

  public void setHp(Integer hp) {
    this.hp = hp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PokemonEntity that)) {
      return false;
    }
    return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(status, that.status) && Objects.equals(maxWeight,
        that.maxWeight) && Objects.equals(minWeight, that.minWeight)
        && Objects.equals(maxHeight, that.maxHeight) && Objects.equals(minHeight,
        that.minHeight) && Objects.equals(cp, that.cp) && Objects.equals(hp,
        that.hp) && Arrays.equals(cry, that.cry);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, name, status, maxWeight, minWeight, maxHeight, minHeight, cp, hp);
    result = 31 * result + Arrays.hashCode(cry);
    return result;
  }
}
