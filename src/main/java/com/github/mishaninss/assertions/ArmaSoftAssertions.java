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
import org.assertj.core.api.SoftAssertions;

import javax.annotation.CheckReturnValue;

/**
 * Entry point for soft assertions of different data types.
 */
public class ArmaSoftAssertions extends SoftAssertions {

    /**
     * Creates a new "soft" instance of <code>{@link ArmaElementAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created "soft" assertion object.
     */
    @CheckReturnValue
    public ArmaElementAssert assertThat(ArmaElement actual) {
        return proxy(ArmaElementAssert.class, ArmaElement.class, actual);
    }

}
