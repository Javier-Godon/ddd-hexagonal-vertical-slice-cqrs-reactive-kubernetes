package es.bluesolution.pokedex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.bluesolution.pokedex.domain.FormName.NotValidFormNameException;
import org.junit.jupiter.api.Test;

class FormNameTest {

  @Test
  void itShouldCreateAFormNameWhenAFormNameStringIsProvided() {
    // Given
    String stringFormName = "formName";
    // When
    FormName underTest = FormName.of(stringFormName);
    // Then
    assertThat(underTest.getValue()).isEqualTo(stringFormName);
  }

  @Test
  void itShouldNotCreateAFormNameWhenANullIsProvided() {
    assertThatThrownBy(() -> {
      FormName.of(null);
    }).isInstanceOf(NotValidFormNameException.class);
  }

  @Test
  void itShouldNotCreateAFormNameWhenAnEmptyStringIsProvided() {
    // Given
    String stringFormName = "";
    // When
    // Then
    assertThatThrownBy(() -> {
      FormName.of(stringFormName);
    }).isInstanceOf(NotValidFormNameException.class);
  }

}