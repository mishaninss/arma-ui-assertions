/*
 * Copyright 2018 Sergey Mishanin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mishaninss.assertions;

import com.github.mishaninss.html.containers.ArmaContainer;
import com.github.mishaninss.html.elements.ArmaElement;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractArmaContainerAssert<S extends AbstractArmaContainerAssert<S, A>, A extends ArmaContainer> extends AbstractObjectAssert<S, A> {

    /**
     * Creates a new <code>{@link AbstractArmaContainerAssert}</code> to make assertions on actual ArmaElement.
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
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is true
        if (!actual.isDisplayed(shouldWait)) {
            failWithMessage("\nExpecting that container is displayed but is not.");
        }

        // return the current assertion for method chaining
        return myself;
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
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is false
        if (actual.isDisplayed(shouldWait)) {
            failWithMessage("\nExpecting that container is not displayed but is.");
        }

        // return the current assertion for method chaining
        return myself;
    }

    public S allElementsAreDisplayed() {
        return allElementsAreDisplayed(false);
    }

    public S allElementsAreDisplayed(boolean shouldWait) {
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is false
        ArmaSoftAssertions softAssertions = new ArmaSoftAssertions();
        actual.getElements().values().stream()
                .filter(element -> !element.isOptional())
                .forEach(element -> softAssertions.assertThat((ArmaElement) element).isDisplayed(shouldWait));
        softAssertions.assertAll();

        // return the current assertion for method chaining
        return myself;
    }

    private String buildDescription() {
        return actual.getLoggableName() + " => " + actual.getLocator();
    }

}
