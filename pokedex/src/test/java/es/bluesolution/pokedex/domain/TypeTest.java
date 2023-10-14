package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;

import es.bluesolution.pokedex.framework.ddd.UUIDIdentifier;
import org.junit.jupiter.api.Test;

class TypeTest {

  @Test
  void itShouldCreateATypeWhenAProperIdentifierAndFormNameAreProvided() {
    // Given
    UUIDIdentifier uuidIdentifier = UUIDIdentifier.of("26afc957-b059-4594-9cc3-84e705f55737");
    UUIDIdentifier pokemonUuidIdentifier = UUIDIdentifier.of(
        "6feec070-c675-412f-8ed7-5cb128f89f00");
    FormName formName = FormName.of("grass");
    // When
    Type underTest = Type.of(uuidIdentifier, pokemonUuidIdentifier, formName);
    // Then
    assertThat(underTest.getFormName()).isEqualTo(formName);
    assertThat(underTest.getId()).isEqualTo(uuidIdentifier);
    assertThat(underTest.getPokemonId()).isEqualTo(pokemonUuidIdentifier);
  }

}