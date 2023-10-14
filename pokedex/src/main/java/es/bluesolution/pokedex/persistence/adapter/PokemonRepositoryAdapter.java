package es.bluesolution.pokedex.persistence.adapter;

import es.bluesolution.pokedex.domain.Cp;
import es.bluesolution.pokedex.domain.Cry;
import es.bluesolution.pokedex.domain.Dimension;
import es.bluesolution.pokedex.domain.Endurance;
import es.bluesolution.pokedex.domain.Height;
import es.bluesolution.pokedex.domain.Hp;
import es.bluesolution.pokedex.domain.Name;
import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.Status;
import es.bluesolution.pokedex.domain.Weight;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import es.bluesolution.pokedex.persistence.PokemonEntity;
import es.bluesolution.pokedex.persistence.PokemonSpringRepository;
import java.util.Base64;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PokemonRepositoryAdapter implements PokemonRepository {

  private final PokemonSpringRepository springRepository;


  public PokemonRepositoryAdapter(PokemonSpringRepository springRepository) {
    this.springRepository = springRepository;
  }

  @Override
  public Flux<Pokemon> findAll() {
    return springRepository.findAll().map(fromEntityToDomain);
  }

  @Override
  public Mono<Pokemon> findById(UUID id) {
    return springRepository.findById(id).map(fromEntityToDomain);
  }

  @Override
  public Flux<Pokemon> findByNameContaining(String name) {
    return springRepository.findByNameContaining(name).map(fromEntityToDomain);
  }

  @Override
  public Flux<Pokemon> findByType(String type) {
    return null;
  }

  @Override
  @Transient
  public Mono<Pokemon> save(Pokemon pokemon) {
    PokemonEntity entityToSave = PokemonEntity.of(pokemon);
    assert entityToSave.getId() != null;
    return springRepository.findById(entityToSave.getId())
        .flatMap(found -> springRepository.save(entityToSave))
        .switchIfEmpty(Mono.defer(() -> springRepository.save(setAsCreate.apply(entityToSave))))
        .map(fromEntityToDomain);
  }

  private final UnaryOperator<PokemonEntity> setAsCreate = pokemonEntity -> {
    pokemonEntity.setAsCreate();
    return pokemonEntity;
  };

  private final Function<PokemonEntity, Pokemon> fromEntityToDomain = pokemonEntity -> {
    Dimension dimension = new Dimension(
        Weight.of(pokemonEntity.getMaxWeight()),
        Weight.of(pokemonEntity.getMinWeight()),
        Height.of(pokemonEntity.getMaxHeight()),
        Height.of(pokemonEntity.getMaxHeight())
    );
    Endurance endurance = new Endurance(
        Cp.of(pokemonEntity.getCp()),
        Hp.of(pokemonEntity.getHp())
    );
    return Pokemon.of(
        UUIDIdentifier.of(pokemonEntity.getId()),
        Name.of(pokemonEntity.getName()),
        Status.of(pokemonEntity.getStatus()),
        dimension,
        endurance,
        Cry.of(Base64.getEncoder().encodeToString(pokemonEntity.getCry())));
  };


}
