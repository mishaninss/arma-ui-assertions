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
 * {@link ArmaElement} specific assertions
 * <p>
 * Although this class is not final to allow Soft assertions proxy, if you wish to extend it,
 * extend {@link AbstractArmaElementAssert} instead.
 */
public class ArmaElementAssert extends AbstractArmaElementAssert<ArmaElementAssert, ArmaElement> {

    /**
     * Creates a new <code>{@link ArmaElementAssert}</code> to make assertions on actual ArmaElement.
     *
     * @param actual the ArmaElement we want to make assertions on.
     */
    public ArmaElementAssert(ArmaElement actual) {
        super(actual, ArmaElementAssert.class);
    }

    /**
     * An entry point for ArmaElementAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
     * With a static import, one can write directly: <code>assertThat(myArmaElement)</code> and get specific assertion with code completion.
     *
     * @param actual the ArmaElement we want to make assertions on.
     * @return a new <code>{@link ArmaElementAssert}</code>
     */
    @CheckReturnValue
    public static ArmaElementAssert assertThat(ArmaElement actual) {
        return new ArmaElementAssert(actual);
    }
}
