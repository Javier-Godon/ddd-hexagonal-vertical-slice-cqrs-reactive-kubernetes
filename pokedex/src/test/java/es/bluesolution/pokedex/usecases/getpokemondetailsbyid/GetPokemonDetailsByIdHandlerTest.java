package es.bluesolution.pokedex.usecases.getpokemondetailsbyid;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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
import es.bluesolution.pokedex.exceptions.PokemonNotFoundException;
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import es.bluesolution.pokedex.usecases.getallpokemons.GetAllPokemonsQuery;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class GetPokemonDetailsByIdHandlerTest {

  @Mock
  private PokemonRepository pokemonRepository;

  @Mock
  private TypeRepository typeRepository;

  @InjectMocks
  private GetPokemonDetailsByIdHandler underTest;

  @Test
  void itShouldHandleAProperQuery() {
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

    given(pokemonRepository.findById(any())).willReturn(Mono.just(pikachu));
    GetPokemonDetailsByIdQuery query = new GetPokemonDetailsByIdQuery(pikachuId);

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

    given(typeRepository.findByPokemonId(any())).willReturn(Flux.fromIterable(types));

    // When
    CorePublisher<GetPokemonDetailsByIdResult> publisher = underTest.handle(query);
    // Then
    StepVerifier
        .create(publisher)
        .expectNextMatches(result ->
            result.getPokemon().getName().getValue().equals("Pikachu")
                && result.getPokemon().getStatus().getValue().name().equals("FAVORITE")
                && result.getPokemon().getCp().getValue().equals(7_777)
                && result.getPokemon().getHp().getValue().equals(9_998)
                && result.getPokemon().getMaxWeight().getValue().equals(10_000)
                && result.getPokemon().getMinWeight().getValue().equals(9_000)
                && result.getPokemon().getMaxHeight().getValue().equals(5_000)
                && result.getPokemon().getMinHeight().getValue().equals(4_500)
                && result.getPokemon().getCry().getValue().equals("aG9sYXF1ZRhbA==")
                && result.getTypes().get(0).getPokemonId().getUuid().toString()
                .equals(pikachuId)
                && result.getTypes().get(0).getFormName().getValue()
                .equals("grass")
                && result.getTypes().get(1).getPokemonId().getUuid().toString()
                .equals(pikachuId)
                && result.getTypes().get(1).getFormName().getValue()
                .equals("bug")
        )
        .expectComplete()
        .verify();
  }

  @Test
  void itShouldThrowAnExceptionWhenTheWrongQueryIsReceived() {
    GetAllPokemonsQuery wrongQuery = new GetAllPokemonsQuery();
    assertThatThrownBy(() -> underTest.handle(wrongQuery)).isInstanceOf(
        HandlerConfigurationException.class);
  }

  @Test
  void itShouldThrowAnExceptionWhenPokemonIsNotFound() {
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

    given(pokemonRepository.findById(any())).willReturn(Mono.empty());
    given(typeRepository.findByPokemonId(any())).willReturn(Flux.empty());

    // Then
    GetPokemonDetailsByIdQuery query = new GetPokemonDetailsByIdQuery(pikachuId);

    StepVerifier.create(underTest.handle(query))
        .expectErrorMatches(throwable -> throwable instanceof PokemonNotFoundException)
        .verify();

  }
}