package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.elements.ArmaElement;

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
