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
import org.assertj.core.api.IntegerAssert;
import com.github.mishaninss.arma.html.containers.table.Table;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.utils.Preconditions;

import java.util.Map;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractTableAssert<S extends AbstractTableAssert<S, A>, A extends Table> extends AbstractObjectAssert<S, A> {

    /**
     * Creates a new <code>{@link AbstractTableAssert}</code> to make assertions on actual ArmaElement.
     *
     * @param actual the ArmaElement we want to make assertions on.
     */
    protected AbstractTableAssert(A actual, Class<S> selfType) {
        super(actual, selfType);
    }

    public S containsColumnValue(String columnName, String value) {
        isNotNull();
        Preconditions.checkNotBlank(columnName, "columnName");

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        int index = actual.findRow(columnName, value);
        if (index <= 0) {
            failWithMessage("Таблица не содержит записей со значением [%s] в колонке [%s]", value, columnName);
        }

        return myself;
    }

    public IntegerAssert rowsCount() {
        IntegerAssert integerAssert = new IntegerAssert(actual.getRowsCount());
        integerAssert.as("Количество строк в таблице " + buildDescription());
        return integerAssert;
    }

    public S containsColumnValues(Map<String, String> data) {
        isNotNull();
        Preconditions.checkNotNull(data, "data");

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        int index = actual.findRowIndex(data);
        if (index <= 0) {
            failWithMessage("Таблица не содержит записей с указанными значениями: %s", data.toString());
        }

        return myself;
    }

    @SuppressWarnings("unchecked")
    public ArmaColumnAssert column(String columnName) {
        isNotNull();
        Preconditions.checkNotBlank(columnName, "columnName");

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        return ArmaAssertions.assertThat(actual.findColumnByName(columnName));
    }

    private String buildDescription() {
        return actual.getLoggableName() + " => " + actual.getLocatorsPath();
    }

    public S isDisplayed() {
        return isDisplayed(false);
    }

    public S isDisplayed(boolean shouldWait) {
        // check that actual Table we want to make assertions on is not null.
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        // check that property call/field access is true
        if (!actual.isDisplayed(shouldWait)) {
            failWithMessage("\nТаблица не отображается");
        }

        // return the current assertion for method chaining
        return myself;
    }

}
