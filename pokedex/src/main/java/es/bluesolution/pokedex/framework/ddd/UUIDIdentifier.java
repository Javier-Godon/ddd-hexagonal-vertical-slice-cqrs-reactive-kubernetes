package es.bluesolution.pokedex.framework.ddd;

import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class UUIDIdentifier implements Identifier {

  private final UUID uuid;

  private UUIDIdentifier(UUID uuid) {
    this.uuid = uuid;
  }

  /**
   * Creates a UUIDIdentifier from a UUID
   *
   * @param uuid the given UUID
   * @return the UUID identifier
   */
  public static UUIDIdentifier of(UUID uuid) {
    return new UUIDIdentifier(Objects.requireNonNull(uuid));
  }

  /**
   * Creates a UUIDIdentifier from a String
   *
   * @param id the given String
   * @return the UUID identifier
   */
  public static UUIDIdentifier of(String id) {
    return new UUIDIdentifier(UUID.fromString(Objects.requireNonNull(id)));
  }

  /**
   * Creates a random UUID
   *
   * @return the generated UUID
   */
  public static UUIDIdentifier random() {
    return new UUIDIdentifier(UUID.randomUUID());
  }
}
