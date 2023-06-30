/*
 * Copyright (c) 2021 Sergey Mishanin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.data.DataObject;
import com.github.mishaninss.arma.html.containers.ArmaContainer;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.interfaces.IInteractiveElement;
import com.github.mishaninss.arma.utils.Preconditions;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;
import org.awaitility.Awaitility;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractArmaContainerAssert<S extends AbstractArmaContainerAssert<S, A>, A extends ArmaContainer> extends
    AbstractObjectAssert<S, A> {

  /**
   * Creates a new <code>{@link AbstractArmaContainerAssert}</code> to make assertions on actual
   * ArmaElement.
   *
   * @param actual the ArmaElement we want to make assertions on.
   */
  protected AbstractArmaContainerAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  /**
   * Verifies that the actual ArmaElement is displayed.
   *
   * @return this assertion object.
   * @throws AssertionError - if the actual ArmaElement is not displayed.
   */
  public S isDisplayed() {
    return isDisplayed(false);
  }

  public S isDisplayed(boolean shouldWait) {
    isNotNull();

    if (StringUtils.isNotBlank(actual.getLocator())) {
      if (StringUtils.isBlank(descriptionText())) {
        as(buildDescription());
      }
      if (!actual.isDisplayed(shouldWait)) {
        failWithMessage("\nКонтейнер не отображается");
      }
      return myself;
    } else {
      return allElementsAreDisplayed(shouldWait);
    }
  }

  /**
   * Verifies that the actual ArmaElement is not displayed.
   *
   * @return this assertion object.
   * @throws AssertionError - if the actual ArmaElement is displayed.
   */
  public S isNotDisplayed() {
    return isNotDisplayed(false);
  }

  public S isNotDisplayed(boolean shouldWait) {
    isNotNull();

    String message = StringUtils.isBlank(info.overridingErrorMessage()) ?
        "Контейнер отображается: " + buildDescription() :
        info.overridingErrorMessage();

    if (actual.isDisplayed(shouldWait)) {
      failWithMessage(message);
    }

    return myself;
  }

  public S allElementsAreDisplayed() {
    return allElementsAreDisplayed(false);
  }

  public S allElementsAreDisplayed(boolean shouldWait) {
    isNotNull();
    if (StringUtils.isBlank(descriptionText())) {
      as(buildDescription());
    }
    assertAllElementsAreDisplayed(shouldWait, actual.getElements().values().stream());
    return myself;
  }

  public S deepAllElementsAreDisplayed(boolean shouldWait) {
    isNotNull();
    if (StringUtils.isBlank(descriptionText())) {
      as(buildDescription());
    }
    assertAllElementsAreDisplayed(shouldWait, actual.deepGetNotOptionalElements().stream());
    return myself;
  }

  public void assertAllElementsAreDisplayed(boolean shouldWait,
      Stream<IInteractiveElement> elementsStream) {
    ArmaSoftAssertions softAssertions = new ArmaSoftAssertions();
    AtomicBoolean displayed = new AtomicBoolean(true);
    elementsStream
        .filter(element -> element instanceof ArmaElement && !element.isOptional())
        .forEach(element -> {
          if (shouldWait && displayed.get()) {
            displayed.set(((ArmaElement) element).waitUntil().quietly().isVisible());
          }
          if (!shouldWait || !displayed.get()) {
            softAssertions.assertThat((ArmaElement) element).isDisplayed(false);
          }
        });
    softAssertions.assertAll();
  }

  public void assertOnlyElementsAreDisplayed(Collection<String> elementIds) {
    assertOnlyElementsAreDisplayed(false, elementIds);
  }

  public void assertOnlyElementsAreDisplayed(boolean shouldWait,
      Collection<String> elementIds) {
    ArmaSoftAssertions assertions = new ArmaSoftAssertions();
    Collection<String> finalElementIds = elementIds.stream().map(DataObject::sanitizeElementId)
        .collect(Collectors.toSet());
    actual.getElements().forEach((elementId, element) -> {
      if (finalElementIds.contains(elementId)) {
        assertions.assertThat(element).isDisplayed(shouldWait);
      } else if (!element.isOptional()) {
        assertions.assertThat(element).isNotDisplayed(shouldWait);
      }
    });
    assertions.assertAll();
  }

  public S containsValues(Map<String, String> expectedValues) {
    isNotNull();
    Preconditions.checkNotNull(expectedValues, "expectedValues");

    if (StringUtils.isBlank(descriptionText())) {
      as(buildDescription());
    }

    ArmaSoftAssertions softAssertions = new ArmaSoftAssertions();
    expectedValues.forEach((elementId, expectedValue) ->
        softAssertions.assertThat(actual.getElement(elementId))
            .value()
            .isEqualTo(expectedValue)
    );
    softAssertions.assertAll();
    return myself;
  }

  public S containsValues(Map<String, String> expectedValues, int timeout) {
    isNotNull();
    Preconditions.checkNotNull(expectedValues, "expectedValues");

    if (StringUtils.isBlank(descriptionText())) {
      as(buildDescription());
    }
    Awaitility.await().atMost(timeout, TimeUnit.SECONDS)
        .untilAsserted(() -> {
          ArmaSoftAssertions softAssertions = new ArmaSoftAssertions();
          expectedValues.forEach((elementId, expectedValue) ->
              softAssertions.assertThat(actual.getElement(elementId))
                  .value()
                  .isEqualTo(expectedValue)
          );
          softAssertions.assertAll();
        });
    return myself;
  }

  public ArmaElementAssert element(String elementId) {
    isNotNull();
    Preconditions.checkNotBlank(elementId, "elementId");
    return ArmaAssertions.assertThat((ArmaElement) actual.getElement(elementId));
  }

  private String buildDescription() {
    String description = actual.getLoggableName();
    String locators = actual.getLocatorsPath();
    return StringUtils.isNotBlank(locators) ? description + " => " + locators : description;
  }

}
