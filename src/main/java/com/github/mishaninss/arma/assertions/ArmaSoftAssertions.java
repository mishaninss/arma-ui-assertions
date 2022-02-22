package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.containers.ArmaContainer;
import org.assertj.core.api.SoftAssertions;
import com.github.mishaninss.arma.html.elements.ArmaElement;

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

    @CheckReturnValue
    public ArmaContainerAssert assertThat(ArmaContainer actual) {
        return proxy(ArmaContainerAssert.class, ArmaContainer.class, actual);
    }

}