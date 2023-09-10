package io.algostrategy.client.coinmarketcap.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    /**
     * Concat integers to fixed length string.
     *
     * @param arr       int arrays
     * @param maxLength max string length
     * @return packs
     */
    public static List<String> concatIntToFixedStr(Integer[] arr, int maxLength) {

        if (arr.length == 0) return new ArrayList<>();

        List<String> packs = Lists.newArrayList("");

        for (Integer i : arr) {

            int lastPackIndex = packs.size() - 1;
            String lastPack = packs.get(lastPackIndex);

            if (!lastPack.isEmpty()) {
                lastPack += COMA_SEPARATOR;
            }

            lastPack += i;

            if (lastPack.length() > maxLength) {
                packs.add(i.toString());
            } else {
                packs.set(lastPackIndex, lastPack);
            }
        }

        return packs;
    }
}
