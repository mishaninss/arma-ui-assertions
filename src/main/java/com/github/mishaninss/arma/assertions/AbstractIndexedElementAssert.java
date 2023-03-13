package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.composites.IndexedElement;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.interfaces.IInteractiveElement;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.IntegerAssert;
import org.assertj.core.api.ListAssert;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractIndexedElementAssert<S extends AbstractIndexedElementAssert<S, A>, A extends IndexedElement<? extends IInteractiveElement>> extends
    AbstractObjectAssert<S, A> {

  /**
   * Creates a new <code>{@link AbstractIndexedElementAssert}</code> to make assertions on actual
   * ArmaElement.
   *
   * @param actual the ArmaElement we want to make assertions on.
   */
  protected AbstractIndexedElementAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  public S containsValue(String value) {
    isNotNull();

    if (StringUtils.isBlank(descriptionText())) {
      as(buildDescription());
    }

    Optional<? extends IInteractiveElement> element = actual.findElement(value);
    if (element.isEmpty()) {
      failWithMessage(String.format("Список элементов не содержит значения [%s]", value));
    }

    return myself;
  }

  public IntegerAssert count() {
    isNotNull();

    if (StringUtils.isBlank(descriptionText())) {
      as("Количество элементов " + buildDescription());
    }
    return new IntegerAssert(actual.count());
  }

  public ListAssert<String> values() {
    isNotNull();

    if (StringUtils.isBlank(descriptionText())) {
      as("Значения элементов " + buildDescription());
    }
    return new ListAssert<>(actual.readValues());
  }

  public ListAssert<String> valuesNormalizedSpace() {
    isNotNull();

    if (StringUtils.isBlank(descriptionText())) {
      as("Значения элементов " + buildDescription());
    }
    return new ListAssert<>(actual.readValues().stream().map(StringUtils::normalizeSpace).collect(
        Collectors.toList()));
  }

  private String buildDescription() {
    return actual.getName();
  }

}