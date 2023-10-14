package es.bluesolution.pokedex.persistence.adapter;

import es.bluesolution.pokedex.domain.FormName;
import es.bluesolution.pokedex.domain.Type;
import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import es.bluesolution.pokedex.persistence.TypeEntity;
import es.bluesolution.pokedex.persistence.TypeSpringRepository;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TypeRepositoryAdapter implements TypeRepository {

  private final TypeSpringRepository springRepository;

  public TypeRepositoryAdapter(TypeSpringRepository springRepository) {
    this.springRepository = springRepository;
  }

  @Override
  public Mono<Type> findById(UUID id) {
    return springRepository.findById(id).map(fromEntityToDomain);
  }

  @Override
  public Flux<Type> findByPokemonId(UUID id) {
    return springRepository.findByPokemonId(id).map(fromEntityToDomain);
  }

  @Override
  public Flux<Type> findByFormNameContaining(String type){
    return springRepository.findByFormNameContaining(type).map(fromEntityToDomain);
  }

  private final Function<TypeEntity, Type> fromEntityToDomain = typeEntity ->
      Type.of(
          UUIDIdentifier.of(typeEntity.getId()),
          UUIDIdentifier.of(typeEntity.getPokemonId()),
          FormName.of(typeEntity.getFormName())
      );

}
