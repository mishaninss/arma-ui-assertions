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

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.elements.interfaces.ISelectable;
import com.github.mishaninss.arma.html.interfaces.INamed;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractSelectableAssert<S extends AbstractSelectableAssert<S, A>, A extends ISelectable> extends AbstractObjectAssert<S, A> {

    /**
     * Creates a new <code>{@link AbstractSelectableAssert}</code> to make assertions on actual ArmaElement.
     *
     * @param actual the ArmaElement we want to make assertions on.
     */
    protected AbstractSelectableAssert(A actual, Class<S> selfType) {
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

    public S isSelected() {
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        if (!actual.isSelected()) {
            failWithMessage("Элемент не отмечет");
        }

        return myself;
    }

    public S isNotSelected() {
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        if (actual.isSelected()) {
            failWithMessage("Элемент отмечет");
        }

        return myself;
    }

    private String buildDescription() {
        return ((INamed) actual).getLoggableName();
    }

}