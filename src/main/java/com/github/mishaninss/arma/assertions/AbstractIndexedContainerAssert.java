package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.data.DataObject;
import com.github.mishaninss.arma.html.containers.ArmaContainer;
import com.github.mishaninss.arma.html.containers.IndexedContainer;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.IntegerAssert;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractIndexedContainerAssert<S extends AbstractIndexedContainerAssert<S, A>, A extends IndexedContainer> extends
    AbstractObjectAssert<S, A> {

  /**
   * Creates a new <code>{@link AbstractIndexedContainerAssert}</code> to make assertions on actual
   * ArmaElement.
   *
   * @param actual the ArmaElement we want to make assertions on.
   */
  protected AbstractIndexedContainerAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  public S contains(Predicate<ArmaContainer> predicate) {
    isNotNull();

    String message = "Не найден контейнер " + buildDescription() + ", удовлетворяющий условию";

    Optional<ArmaContainer> element = actual.findContainer(predicate);
    if (element.isEmpty()) {
      failWithMessage(message);
    }

    return myself;
  }

  public S contains(Map<String, String> expected) {
    isNotNull();

    String message =
        "Не найден контейнер " + buildDescription() + ", содержащий значения " + expected;

    Optional<ArmaContainer> element = actual.findContainer(expected);
    if (element.isEmpty()) {
      failWithMessage(message);
    }

    return myself;
  }

  public S containsAll(List<Map<String, String>> expected) {
    isNotNull();

    if (StringUtils.isNoneBlank(descriptionText(), info.overridingErrorMessage())) {
      as("Values of %s", buildDescription());
    }

    List<Map<String, String>> act = actual.readAll(expected.get(0).keySet());
    Assertions.assertThat(act).containsAll(DataObject.sanitizeKeys(expected));

    return myself;
  }

  public S doesNotContainAny(List<Map<String, String>> expected) {
    isNotNull();

    if (StringUtils.isNoneBlank(descriptionText(), info.overridingErrorMessage())) {
      as("Values of %s", buildDescription());
    }

    List<Map<String, String>> act = actual.readAll(expected.get(0).keySet());
    Assertions.assertThat(act).doesNotContainAnyElementsOf(DataObject.sanitizeKeys(expected));

    return myself;
  }

  public S containsIgnoreCase(Map<String, String> expected) {
    isNotNull();

    String message =
        "Не найден контейнер " + buildDescription() + ", содержащий значения " + expected;

    Optional<ArmaContainer> element = actual.findContainer(container -> {
      for (Map.Entry<String, String> entry : expected.entrySet()) {
        if (!StringUtils.equalsIgnoreCase(((ArmaContainer) container).readValue(entry.getKey()),
            entry.getValue())) {
          return false;
        }
      }
      return true;
    });
    if (element.isEmpty()) {
      failWithMessage(message);
    }

    return myself;
  }

  public S doesNotContain(Predicate<ArmaContainer> predicate) {
    isNotNull();

    String message = "Найден контейнер " + buildDescription() + ", удовлетворяющий условию";

    Optional<ArmaContainer> element = actual.findContainer(predicate);
    if (element.isPresent()) {
      failWithMessage(message);
    }

    return myself;
  }

  public S doesNotContain(Map<String, String> data) {
    isNotNull();

    String message = "Найден контейнер " + buildDescription() + ", удовлетворяющий условию";

    Optional<ArmaContainer> element = actual.findContainer(data);
    if (element.isPresent()) {
      failWithMessage(message);
    }

    return myself;
  }

  public IntegerAssert count() {
    isNotNull();

    if (StringUtils.isBlank(descriptionText())) {
      as("Количество контейнеров " + buildDescription());
    }
    return new IntegerAssert(actual.count());
  }

  private String buildDescription() {
    return actual.getName();
  }

}