package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;

import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class PokemonTest {

  @Test
  void itShouldCreateAPokemonWhenNotNullParametersAreProvided() {
    // Given
    Dimension dimensionPikachu = new Dimension(
        Weight.of(10_000),
        Weight.of(9_000),
        Height.of(5_000),
        Height.of(4_500)
    );
    Endurance endurancePikachu = new Endurance(
        Cp.of(7_777),
        Hp.of(9_998)
    );
    // When
    Pokemon underTest = Pokemon.of(
        UUIDIdentifier.of("df2908cc-6a3c-4950-9313-10bab26c1774"),
        Name.of("Pikachu"),
        Status.of(PokemonStatusEnum.FAVORITE),
        dimensionPikachu,
        endurancePikachu,
        Cry.of("aG9sYXF1ZRhbA==")
    );
    // Then
    assertThat(underTest.getName().getValue()).isEqualTo("Pikachu");
    assertThat(underTest.getStatus().getValue().name()).isEqualTo("FAVORITE");
    assertThat(underTest.getCp().getValue()).isEqualTo(7_777);
    assertThat(underTest.getHp().getValue()).isEqualTo(9_998);
    assertThat(underTest.getMaxWeight().getValue()).isEqualTo(10_000);
    assertThat(underTest.getMinWeight().getValue()).isEqualTo(9_000);
    assertThat(underTest.getMaxHeight().getValue()).isEqualTo(5_000);
    assertThat(underTest.getMinHeight().getValue()).isEqualTo(4_500);
    assertThat(underTest.getCry().getValue()).isEqualTo("aG9sYXF1ZRhbA==");
  }

  @Test
  void itShouldMutateTheStatusOfAPokemon() {
    // Given
    Dimension dimensionPikachu = new Dimension(
        Weight.of(10_000),
        Weight.of(9_000),
        Height.of(5_000),
        Height.of(4_500)
    );
    Endurance endurancePikachu = new Endurance(
        Cp.of(7_777),
        Hp.of(9_998)
    );
    Pokemon underTest = Pokemon.of(
        UUIDIdentifier.of("df2908cc-6a3c-4950-9313-10bab26c1774"),
        Name.of("Pikachu"),
        Status.of(PokemonStatusEnum.FAVORITE),
        dimensionPikachu,
        endurancePikachu,
        Cry.of("aG9sYXF1ZRhbA==")
    );
    // When
    Status newStatus = Status.of("REGULAR");
    underTest.mutateStatus(newStatus);
    // Then
    StepVerifier
        .create(underTest.mutateStatus(newStatus))
        .expectNextMatches(pokemon -> pokemon.getStatus().getValue().name().equals("REGULAR"))
        .expectComplete()
        .verify();
  }

}