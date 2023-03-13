package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.containers.ArmaContainer;
import com.github.mishaninss.arma.html.containers.IndexedContainer;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.interfaces.IInteractiveElement;
import javax.annotation.CheckReturnValue;
import org.assertj.core.api.SoftAssertions;

/**
 * Entry point for soft assertions of different data types.
 */
public class ArmaSoftAssertions extends SoftAssertions {

  /**
   * Creates a new "soft" instance of <code>{@link ArmaElementAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public ArmaElementAssert assertThat(ArmaElement actual) {
    return proxy(ArmaElementAssert.class, ArmaElement.class, actual);
  }

  @CheckReturnValue
  public ArmaContainerAssert assertThat(ArmaContainer actual) {
    return proxy(ArmaContainerAssert.class, ArmaContainer.class, actual);
  }

  @CheckReturnValue
  public ArmaIndexedContainerAssert assertThat(IndexedContainer<?> actual) {
    return proxy(ArmaIndexedContainerAssert.class, IndexedContainer.class, actual);
  }

  @CheckReturnValue
  public IInteractiveElementAssert assertThat(IInteractiveElement actual) {
    return proxy(IInteractiveElementAssert.class, IInteractiveElement.class, actual);
  }

}