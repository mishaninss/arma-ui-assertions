package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.containers.ArmaContainer;
import com.github.mishaninss.arma.html.containers.table.Column;
import com.github.mishaninss.arma.html.containers.table.Table;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.elements.interfaces.ISelectable;
import com.github.mishaninss.arma.html.interfaces.IInteractiveElement;
import javax.annotation.CheckReturnValue;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory
 * for the type-specific assertion objects.
 */
public class ArmaAssertions {

  /**
   * Creates a new instance of <code>{@link ArmaElementAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static ArmaElementAssert assertThat(ArmaElement actual) {
    return new ArmaElementAssert(actual);
  }

  @CheckReturnValue
  public static ArmaContainerAssert assertThat(ArmaContainer actual) {
    return new ArmaContainerAssert(actual);
  }

  @CheckReturnValue
  public static ArmaTableAssert assertThat(Table actual) {
    return new ArmaTableAssert(actual);
  }

  @CheckReturnValue
  public static ArmaColumnAssert assertThat(Column<IInteractiveElement> actual) {
    return new ArmaColumnAssert(actual);
  }

  @CheckReturnValue
  public static ArmaSelectableAssert assertThat(ISelectable actual) {
    return new ArmaSelectableAssert(actual);
  }

  @CheckReturnValue
  public static IInteractiveElementAssert assertThat(IInteractiveElement actual) {
    return new IInteractiveElementAssert(actual);
  }

  /**
   * Creates a new <code>{@link ArmaAssertions}</code>.
   */
  protected ArmaAssertions() {
    // empty
  }
}