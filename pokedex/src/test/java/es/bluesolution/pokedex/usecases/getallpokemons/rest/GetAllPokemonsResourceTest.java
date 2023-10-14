package es.bluesolution.pokedex.usecases.getallpokemons.rest;

import static org.mockito.BDDMockito.given;

import es.bluesolution.pokedex.domain.Cp;
import es.bluesolution.pokedex.domain.Cry;
import es.bluesolution.pokedex.domain.Dimension;
import es.bluesolution.pokedex.domain.Endurance;
import es.bluesolution.pokedex.domain.Height;
import es.bluesolution.pokedex.domain.Hp;
import es.bluesolution.pokedex.domain.Name;
import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.PokemonStatusEnum;
import es.bluesolution.pokedex.domain.Status;
import es.bluesolution.pokedex.domain.Weight;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = GetAllPokemonsResource.class)
class GetAllPokemonsResourceTest {

  @MockBean(name = "getAllPokemonsRegister")
  private CommandQueryMediator commandQueryMediator;

  @MockBean
  private PokemonRepository pokemonRepository;

  @MockBean
  private TypeRepository typeRepository;

  @Autowired
  private WebTestClient webTestClient;

  @Captor
  private ArgumentCaptor<CommandQuery> commandQueryArgumentCaptor;


  @Test
  void itShouldRetrieveTheListOfPokemonsWhenReceivesAProperQuery() throws Exception {
    // Given
    Dimension dimensionPikachu = new Dimension(
        Weight.of(10000),
        Weight.of(9000),
        Height.of(5000),
        Height.of(4500)
    );
    Endurance endurancePikachu = new Endurance(
        Cp.of(7777),
        Hp.of(9998)
    );
    Pokemon pikachu = Pokemon.of(
        UUIDIdentifier.of("df2908cc-6a3c-4950-9313-10bab26c1774"),
        Name.of("Pikachu"),
        Status.of(PokemonStatusEnum.FAVORITE),
        dimensionPikachu,
        endurancePikachu,
        Cry.of("aG9sYXF1ZRhbA==")
    );

    Dimension dimensionSnorlax = new Dimension(
        Weight.of(10001),
        Weight.of(9001),
        Height.of(5001),
        Height.of(4501)
    );
    Endurance enduranceSnorlax = new Endurance(
        Cp.of(7778),
        Hp.of(9999)
    );
    Pokemon snorlax = Pokemon.of(
        UUIDIdentifier.of("140fcb8e-f32b-4136-9ed1-97ce12589486"),
        Name.of("Snorlax"),
        Status.of(PokemonStatusEnum.REGULAR),
        dimensionSnorlax,
        enduranceSnorlax,
        Cry.of("aG9sYXF1ZRhbA==")
    );

    List<Pokemon> pokemonList = new ArrayList<>();
    pokemonList.add(pikachu);
    pokemonList.add(snorlax);
    Publisher<Object> pokemonFlux = Flux.fromIterable(pokemonList);
    given(commandQueryMediator.execute(commandQueryArgumentCaptor.capture())).willReturn(
        Flux.fromIterable(pokemonList));
    // When
    ResponseSpec response = webTestClient.get()
        .uri("/pokedex/pokemon")
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .exchange();

    response
        .expectStatus().isOk()
        .expectBodyList(PokemonResponse.class);

  }

}