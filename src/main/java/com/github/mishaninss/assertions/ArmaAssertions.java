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

import com.github.mishaninss.html.elements.ArmaElement;

import javax.annotation.CheckReturnValue;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
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

    /**
     * Creates a new <code>{@link ArmaAssertions}</code>.
     */
    protected ArmaAssertions() {
        // empty
    }
}
