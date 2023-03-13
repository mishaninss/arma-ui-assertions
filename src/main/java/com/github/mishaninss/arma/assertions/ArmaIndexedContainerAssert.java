package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.containers.ArmaContainer;
import com.github.mishaninss.arma.html.containers.IndexedContainer;
import com.github.mishaninss.arma.html.containers.interfaces.IBatchElementsContainer;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import javax.annotation.CheckReturnValue;

/**
 * {@link ArmaElement} specific assertions
 * <p>
 * Although this class is not final to allow Soft assertions proxy, if you wish to extend it, extend
 * {@link AbstractArmaElementAssert} instead.
 */
public class ArmaIndexedContainerAssert extends
    AbstractIndexedContainerAssert<ArmaIndexedContainerAssert, IndexedContainer<?>> {

  /**
   * Creates a new <code>{@link ArmaIndexedContainerAssert}</code> to make assertions on actual
   * ArmaElement.
   *
   * @param actual the ArmaElement we want to make assertions on.
   */
  public ArmaIndexedContainerAssert(IndexedContainer<?> actual) {
    super(actual, ArmaIndexedContainerAssert.class);
  }

  /**
   * An entry point for ArmaElementAssert to follow AssertJ standard <code>assertThat()</code>
   * statements.<br> With a static import, one can write directly:
   * <code>assertThat(myArmaElement)</code> and get specific assertion with code completion.
   *
   * @param actual the ArmaElement we want to make assertions on.
   * @return a new <code>{@link ArmaIndexedContainerAssert}</code>
   */
  @CheckReturnValue
  public static ArmaIndexedContainerAssert assertThat(
      IndexedContainer<?> actual) {
    return new ArmaIndexedContainerAssert(actual);
  }
}
