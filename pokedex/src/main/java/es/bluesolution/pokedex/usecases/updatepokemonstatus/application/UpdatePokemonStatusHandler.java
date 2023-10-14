package es.bluesolution.pokedex.usecases.updatepokemonstatus.application;

import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.Status;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.exceptions.PokemonNotFoundException;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.Handler;
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import java.util.UUID;
import java.util.function.BiFunction;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

public class UpdatePokemonStatusHandler implements Handler {

  private final PokemonRepository pokemonRepository;

  public UpdatePokemonStatusHandler(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public CorePublisher<Object> handle(CommandQuery commandQuery) {
    if (!(commandQuery instanceof UpdatePokemonStatusCommand command)) {
      throw HandlerConfigurationException.commandQueryNotExpected(
          UpdatePokemonStatusCommand.class.getSimpleName(),
          commandQuery.getClass().getSimpleName());
    }

    return findCurrentPokemon.apply(command, pokemonRepository)
        .switchIfEmpty(Mono.defer(() -> Mono.error(new PokemonNotFoundException(
            String.format("Pokemon with id %s not found", command.getPokemonId())))))
        .flatMap(pokemon -> pokemon.mutateStatus(Status.of(command.getStatus())))
        .flatMap(pokemon -> pokemon.save(pokemonRepository));
  }

  private final BiFunction<UpdatePokemonStatusCommand, PokemonRepository, Mono<Pokemon>> findCurrentPokemon = (command, repository) ->
      repository.findById(UUID.fromString(command.getPokemonId()));


}
