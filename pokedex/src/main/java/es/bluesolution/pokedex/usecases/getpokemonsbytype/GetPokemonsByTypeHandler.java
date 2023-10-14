package es.bluesolution.pokedex.usecases.getpokemonsbytype;

import es.bluesolution.pokedex.domain.Type;
import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import reactor.core.CorePublisher;

public class GetPokemonsByTypeHandler implements Handler<Type> {

  private final TypeRepository typeRepository;

  public GetPokemonsByTypeHandler(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  @Override
  public CorePublisher<Type> handle(CommandQuery commandQuery) {
    if (!(commandQuery instanceof GetPokemonsByTypeQuery query)) {
      throw HandlerConfigurationException.commandQueryNotExpected(
          GetPokemonsByTypeQuery.class.getSimpleName(), commandQuery.getClass().getSimpleName());
    }

    return typeRepository.findByFormNameContaining(query.getType())
        .distinct(Type::getPokemonId);
  }
}
