package es.bluesolution.pokedex.usecases.updatepokemonstatus.infrastructure.rest;

import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.usecases.updatepokemonstatus.application.UpdatePokemonStatusCommand;
import es.bluesolution.pokedex.usecases.updatepokemonstatus.infrastructure.rest.spec.UpdatePokemonStatusSpec;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/pokedex")
public class UpdatePokemonStatusResource implements UpdatePokemonStatusSpec {

  private final CommandQueryMediator mediator;

  public UpdatePokemonStatusResource(
      @Qualifier("updatePokemonStatusRegister") CommandQueryMediator mediator) {
    this.mediator = mediator;
  }

  @PutMapping(value = "/pokemon/status", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Object> updatePokemonStatus(@RequestBody UpdatePokemonStatusRequest request) {
    UpdatePokemonStatusCommand command = fromRequestToCommand.apply(request);
    Publisher<Object> publisher = mediator.execute(command);
    Mono<Object> monoResult = Mono.from(publisher);
    return monoResult;
//    return Mono.empty();
  }

  private final Function<UpdatePokemonStatusRequest, UpdatePokemonStatusCommand> fromRequestToCommand = request ->
      UpdatePokemonStatusCommand.builder()
          .pokemonId(request.getPokemonId())
          .status(request.getStatus())
          .build();

}
