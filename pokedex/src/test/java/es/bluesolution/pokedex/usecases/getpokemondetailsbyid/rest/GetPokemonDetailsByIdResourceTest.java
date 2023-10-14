package es.bluesolution.pokedex.usecases.getpokemondetailsbyid.rest;

import static org.mockito.BDDMockito.given;

import es.bluesolution.pokedex.domain.Cp;
import es.bluesolution.pokedex.domain.Cry;
import es.bluesolution.pokedex.domain.Dimension;
import es.bluesolution.pokedex.domain.Endurance;
import es.bluesolution.pokedex.domain.FormName;
import es.bluesolution.pokedex.domain.Height;
import es.bluesolution.pokedex.domain.Hp;
import es.bluesolution.pokedex.domain.Name;
import es.bluesolution.pokedex.domain.Pokemon;
import es.bluesolution.pokedex.domain.PokemonStatusEnum;
import es.bluesolution.pokedex.domain.Status;
import es.bluesolution.pokedex.domain.Type;
import es.bluesolution.pokedex.domain.Weight;
import es.bluesolution.pokedex.domain.repository.PokemonRepository;
import es.bluesolution.pokedex.domain.repository.TypeRepository;
import es.bluesolution.pokedex.framework.cqrs.CommandQuery;
import es.bluesolution.pokedex.framework.cqrs.CommandQueryMediator;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import es.bluesolution.pokedex.usecases.getallpokemons.rest.GetAllPokemonsResource;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.GetPokemonDetailsByIdResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = GetAllPokemonsResource.class)
class GetPokemonDetailsByIdResourceTest {

  @MockBean(name = "getPokemonDetailsByIdRegister")
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
  void itShouldRetrieveAPokemonDetailsWhenReceivesAProperQuery() throws Exception {
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
    String pikachuId = "df2908cc-6a3c-4950-9313-10bab26c1774";
    Pokemon pikachu = Pokemon.of(
        UUIDIdentifier.of(pikachuId),
        Name.of("Pikachu"),
        Status.of(PokemonStatusEnum.FAVORITE),
        dimensionPikachu,
        endurancePikachu,
        Cry.of("aG9sYXF1ZRhbA==")
    );

    String typeIdOne = "9cc765a2-0b4f-4d94-8036-05cd3d6d814b";
    String typeIdTwo = "1d66d7d7-d1ac-475e-94f6-251f23bec8f9";

    Type typeOne = Type.of(
        UUIDIdentifier.of(typeIdOne),
        UUIDIdentifier.of(pikachuId),
        FormName.of("grass")
    );

    Type typeTwo = Type.of(
        UUIDIdentifier.of(typeIdTwo),
        UUIDIdentifier.of(pikachuId),
        FormName.of("bug")
    );

    List<Type> types = new ArrayList<>();
    types.add(typeOne);
    types.add(typeTwo);

    GetPokemonDetailsByIdResult getPokemonDetailsByIdResul = GetPokemonDetailsByIdResult.builder()
        .pokemon(pikachu)
        .types(types)
        .build();

    given(commandQueryMediator.execute(commandQueryArgumentCaptor.capture())).willReturn(Mono.just(getPokemonDetailsByIdResul));
    // When
    ResponseSpec response = webTestClient.get()
        .uri("/pokedex/pokemon/details/" + pikachuId)
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .exchange();

    response
        .expectStatus().isOk()
        .expectBodyList(DetailedPokemonResponse.class);

  }


}