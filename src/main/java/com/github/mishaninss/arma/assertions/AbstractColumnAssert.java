package com.github.mishaninss.arma.assertions;

import java.util.ArrayList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractObjectAssert;
import com.github.mishaninss.arma.html.containers.table.Column;
import com.github.mishaninss.arma.html.elements.ArmaElement;
import com.github.mishaninss.arma.html.interfaces.IInteractiveElement;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;

/**
 * Abstract base class for {@link ArmaElement} specific assertions
 */
public abstract class AbstractColumnAssert<S extends AbstractColumnAssert<S, A>, A extends Column<IInteractiveElement>> extends AbstractObjectAssert<S, A> {

    /**
     * Creates a new <code>{@link AbstractColumnAssert}</code> to make assertions on actual ArmaElement.
     *
     * @param actual the ArmaElement we want to make assertions on.
     */
    protected AbstractColumnAssert(A actual, Class<S> selfType) {
        super(actual, selfType);
    }

    public S containsValue(String value) {
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        List<Integer> indexes = actual.findRows(value);
        if (CollectionUtils.isEmpty(indexes)) {
            failWithMessage(String.format("Колонка не содержит записей со значением [%s]", value));
        }

        return myself;
    }

  public ListAssert<String> values() {
    isNotNull();

    if (StringUtils.isBlank(descriptionText())) {
      as(buildDescription());
    }
    return new ListAssert<>(actual.readValues());
  }

    public ArmaElementAssert row(int index) {
        isNotNull();

        if (StringUtils.isBlank(descriptionText())) {
            as(buildDescription());
        }

        return ArmaAssertions.assertThat((ArmaElement) actual.getCell(index));

    }

    private String buildDescription() {
        return actual.getName();
    }

}
