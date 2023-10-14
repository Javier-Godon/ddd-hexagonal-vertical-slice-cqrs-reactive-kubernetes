package es.bluesolution.pokedex.usecases.getallpokemons;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import es.bluesolution.pokedex.framework.cqrs.HandlerConfigurationException;
import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import es.bluesolution.pokedex.usecases.getpokemondetailsbyid.GetPokemonDetailsByIdQuery;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class GetAllPokemonsHandlerUnitTest {

  @Mock
  private PokemonRepository pokemonRepository;

  @InjectMocks
  private GetAllPokemonsHandler underTest;

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
    given(pokemonRepository.findAll()).willReturn(Flux.fromIterable(pokemonList));
    GetAllPokemonsQuery query = new GetAllPokemonsQuery();

    // When
    CorePublisher<Pokemon> publisher = underTest.handle(query);
    // Then
    StepVerifier
        .create(publisher)
        .expectNextMatches(pokemon -> pokemon.getName().getValue().equals("Pikachu"))
        .expectNextMatches(pokemon -> pokemon.getName().getValue().equals("Snorlax"))
        .expectComplete()
        .verify();
  }

  @Test
  void itShouldThrowAnExceptionWhenTheWrongQueryIsReceived() {
    GetPokemonDetailsByIdQuery wrongQuery = new GetPokemonDetailsByIdQuery(
        "140fcb8e-f32b-4136-9ed1-97ce12589486");
    assertThatThrownBy(() -> underTest.handle(wrongQuery)).isInstanceOf(
        HandlerConfigurationException.class);
  }
}
