package com.knowledge_network.support.utils;

import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.AssertException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by pentonbin on 18-1-25
 */
public abstract class Asserts {


    public static void isTrue(boolean expression, ResponseErrorEnum errorEnum) {
        if (!expression) {
            throw new AssertException(errorEnum);
        }
    }

    public static void isNull(Object object, ResponseErrorEnum errorEnum) {
        if (object != null) {
            throw new AssertException(errorEnum);
        }
    }

    public static void notNull(Object object, ResponseErrorEnum errorEnum) {
        if (object == null) {
            throw new AssertException(errorEnum);
        }
    }

    public static void hasLength(String text, ResponseErrorEnum errorEnum) {
        if (!org.springframework.util.StringUtils.hasLength(text)) {
            throw new AssertException(errorEnum);
        }
    }

    public static void hasText(String text, ResponseErrorEnum errorEnum) {
        if (!org.springframework.util.StringUtils.hasText(text)) {
            throw new AssertException(errorEnum);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, ResponseErrorEnum errorEnum) {
        if (org.springframework.util.StringUtils.hasLength(textToSearch) && org.springframework.util.StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new AssertException(errorEnum);
        }
    }

    public static void notEmpty(Object[] array, ResponseErrorEnum errorEnum) {
        if (ObjectUtils.isEmpty(array)) {
            throw new AssertException(errorEnum);
        }
    }

    public static void noNullElements(Object[] array, ResponseErrorEnum errorEnum) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new AssertException(errorEnum);
                }
            }
        }
    }

    public static void notEmpty(Collection<?> collection, ResponseErrorEnum errorEnum) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new AssertException(errorEnum);
        }
    }

    public static void notEmpty(Map<?, ?> map, ResponseErrorEnum errorEnum) {
        if (CollectionUtils.isEmpty(map)) {
            throw new AssertException(errorEnum);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, ResponseErrorEnum errorEnum) {
        notNull(type, errorEnum);
        if (!type.isInstance(obj)) {
            throw new AssertException(errorEnum);
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, ResponseErrorEnum errorEnum) {
        notNull(superType, errorEnum);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new AssertException(errorEnum);
        }
    }

    /**
     * Assert a boolean expression, throwing {@code AssertException}
     * if the test result is {@code false}. Call isTrue if you wish to
     * throw AssertException on an assertion failure.
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
     */
    public static void state(boolean expression, ResponseErrorEnum errorEnum) {
        if (!expression) {
            throw new AssertException(errorEnum);
        }
    }

    public static void isLargerEqualThan(Number left, Number right, ResponseErrorEnum errorEnum) {
        Double oneDouble = (Double) left;
        Double otherDouble = (Double) right;
        if (oneDouble < otherDouble) throw new AssertException(errorEnum);
    }
}
