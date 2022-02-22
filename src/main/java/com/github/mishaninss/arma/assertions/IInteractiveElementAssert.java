package com.github.mishaninss.arma.assertions;

import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.interfaces.IInteractiveElement;
import javax.annotation.CheckReturnValue;

/**
 * {@link ArmaElement} specific assertions
 * <p>
 * Although this class is not final to allow Soft assertions proxy, if you wish to extend it,
 * extend {@link AbstractArmaElementAssert} instead.
 */
public class IInteractiveElementAssert extends AbstractIInteractiveElementAssert<IInteractiveElementAssert, IInteractiveElement> {

    /**
     * Creates a new <code>{@link IInteractiveElementAssert}</code> to make assertions on actual ArmaElement.
     *
     * @param actual the ArmaElement we want to make assertions on.
     */
    public IInteractiveElementAssert(IInteractiveElement actual) {
        super(actual, IInteractiveElementAssert.class);
    }

    /**
     * An entry point for ArmaElementAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
     * With a static import, one can write directly: <code>assertThat(myArmaElement)</code> and get specific assertion with code completion.
     *
     * @param actual the ArmaElement we want to make assertions on.
     * @return a new <code>{@link IInteractiveElementAssert}</code>
     */
    @CheckReturnValue
    public static IInteractiveElementAssert assertThat(IInteractiveElement actual) {
        return new IInteractiveElementAssert(actual);
    }
}
