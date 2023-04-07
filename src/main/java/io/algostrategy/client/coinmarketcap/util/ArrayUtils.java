package io.algostrategy.client.coinmarketcap.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Useful methods for arrays.
 */
@UtilityClass
public class ArrayUtils {

    private final static String COMA_SEPARATOR = ",";

    /**
     * Convert array to string.
     *
     * @param arr for joining
     * @return string or null if array is null
     */
    public static String arrayToString(Object[] arr) {
        if (arr == null) return null;
        return Arrays.stream(arr)
                .map(Object::toString)
                .collect(Collectors.joining(COMA_SEPARATOR));
    }
}
