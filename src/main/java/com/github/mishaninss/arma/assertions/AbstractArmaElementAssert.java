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

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.CharSequenceAssert;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.elements.ElementAttribute;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractArmaElementAssert<S extends AbstractArmaElementAssert<S, A>, A extends ArmaElement> extends AbstractObjectAssert<S, A> {

    /**
     * Creates a new <code>{@link AbstractArmaElementAssert}</code> to make assertions on actual ArmaElement.
     *
     * @param actual the ArmaElement we want to make assertions on.
     */
    protected AbstractArmaElementAssert(A actual, Class<S> selfType) {
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
            failWithMessage("\nЭлемент не отображается");
        }

        // return the current assertion for method chaining
        return myself;
    }

    public S containsClass(String expectedClass) {
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is true
        if (!StringUtils.contains(actual.getAttribute("class"), expectedClass)) {
            failWithMessage("\nExpecting that element contains [%s] class but it does not.", expectedClass);
        }

        // return the current assertion for method chaining
        return myself;
    }

    public S notContainsClass(String expectedClass) {
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is true
        if (StringUtils.contains(actual.getAttribute("class"), expectedClass)) {
            failWithMessage("\nExpecting that element does not contain [%s] class but it does.", expectedClass);
        }

        // return the current assertion for method chaining
        return myself;
    }

    public CharSequenceAssert value() {
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actual.readValue());
        charSequenceAssert.as("значение элемента" + buildDescription());
        return charSequenceAssert;
    }

    public CharSequenceAssert valueIgnoringNewLines() {
        String actualValue = actual.readValue();
        if (StringUtils.isNotBlank(actualValue)){
            actualValue = actualValue.replace("\n", " ");
        }
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actualValue);
        charSequenceAssert.as("значение элемента " + buildDescription() + " без учёта переноса строки");
        return charSequenceAssert;
    }

    public CharSequenceAssert text() {
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actual.read().text());
        charSequenceAssert.as(buildDescription() + " text");
        return charSequenceAssert;
    }

    public CharSequenceAssert fullText() {
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actual.read().fullText());
        charSequenceAssert.as(buildDescription() + " full text");
        return charSequenceAssert;
    }

    public CharSequenceAssert tagName() {
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actual.read().tagName());
        charSequenceAssert.as(buildDescription() + " tag name");
        return charSequenceAssert;
    }

    public CharSequenceAssert attribute(ElementAttribute attribute) {
        return attribute(attribute.getName());
    }

    public CharSequenceAssert attribute(String attribute) {
        Preconditions.checkArgument(StringUtils.isNotBlank(attribute), "name of an attribute cannot be null or blank string");
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actual.getAttribute(attribute));
        charSequenceAssert.as(buildDescription() + " [%s] attribute", attribute);
        return charSequenceAssert;
    }

    public CharSequenceAssert cssValue(String cssValue) {
        Preconditions.checkArgument(StringUtils.isNotBlank(cssValue), "name of an css value cannot be null or blank string");
        CharSequenceAssert charSequenceAssert = new CharSequenceAssert(actual.read().cssValue(cssValue));
        charSequenceAssert.as(buildDescription() + " [%s] css value", cssValue);
        return charSequenceAssert;
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
            failWithMessage("\nExpecting that element is not displayed but is.");
        }

        // return the current assertion for method chaining
        return myself;
    }

    private String buildDescription() {
        return actual.getLoggableName() + " => " + actual.getLocatorsPath();
    }

    /**
     * Verifies that the actual ArmaElement is enabled.
     *
     * @return this assertion object.
     * @throws AssertionError - if the actual ArmaElement is not enabled.
     */
    public S isEnabled() {
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is true
        if (!actual.isEnabled()) {
            failWithMessage("\nExpecting that actual ArmaElement is enabled but is not.");
        }

        // return the current assertion for method chaining
        return myself;
    }

    /**
     * Verifies that the actual ArmaElement is not enabled.
     *
     * @return this assertion object.
     * @throws AssertionError - if the actual ArmaElement is enabled.
     */
    public S isNotEnabled() {
        // check that actual ArmaElement we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is false
        if (actual.isEnabled()) {
            failWithMessage("\nExpecting that actual ArmaElement is not enabled but is.");
        }

        // return the current assertion for method chaining
        return myself;
    }
}
