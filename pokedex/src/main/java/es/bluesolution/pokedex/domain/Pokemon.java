package es.bluesolution.pokedex.domain;

import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.ddd.AggregateRoot;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class Pokemon extends AggregateRoot<UUIDIdentifier> {

  private final Name name;
  private final Status status;
  private final Weight maxWeight;
  private final Weight minWeight;
  private final Height maxHeight;
  private final Height minHeight;
  private final Cp cp;
  private final Hp hp;
  private final Cry cry;
  private List<Type> types;

  private Pokemon(UUIDIdentifier id, Name name, Status status,
      Dimension dimension, Endurance endurance, Cry cry) {
    super(id);
    this.name = name;
    this.status = status;
    this.maxWeight = dimension.maxWeight();
    this.minWeight = dimension.minWeight();
    this.maxHeight = dimension.maxHeight();
    this.minHeight = dimension.minHeight();
    this.cp = endurance.cp();
    this.hp = endurance.hp();
    this.cry = cry;
  }

  public static Pokemon of(UUIDIdentifier id, Name name, Status status, Dimension dimension,
      Endurance endurance, Cry cry) {
    return new Pokemon(id, name, status, dimension, endurance, cry);
  }

  public Mono<Pokemon> save(PokemonRepository pokemonRepository) {
    return pokemonRepository.save(this);
  }

  public Mono<Pokemon> mutateStatus(Status newStatus) {
    Dimension dimension = new Dimension(
        this.getMaxWeight(),
        this.getMinWeight(),
        this.getMaxHeight(),
        this.getMinHeight()
    );
    Endurance endurance = new Endurance(
        this.getCp(),
        this.getHp()
    );
    return Mono.just(new Pokemon(
        this.getId(), this.getName(), newStatus, dimension, endurance, this.getCry()
    ));
  }
}
