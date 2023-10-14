package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.Name.NotValidNameException;
import org.junit.jupiter.api.Test;

class NameTest {

  @Test
  void itShouldCreateANameWhenANameStringIsProvided() {
    // Given
    String stringName = "grass";
    // When
    Name underTest = Name.of(stringName);
    // Then
    assertThat(underTest.getValue()).isEqualTo(stringName);
  }

  @Test
  void itShouldNotCreateANameWhenANullIsProvided() {
    assertThatThrownBy(() -> {
      Name.of(null);
    }).isInstanceOf(NotValidNameException.class);
  }

  @Test
  void itShouldNotCreateANameWhenAnEmptyStringIsProvided() {
    // Given
    String stringName = "";
    // When
    // Then
    assertThatThrownBy(() -> {
      Name.of(stringName);
    }).isInstanceOf(NotValidNameException.class);
  }

}